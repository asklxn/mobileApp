package com.example.sleepit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GraphActivity extends AppCompatActivity {

    private BarChart barChart;
    private TextView textRecommendation;
    private TextView textAverage;  // 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        textAverage = findViewById(R.id.textAverage);
        barChart = findViewById(R.id.chartContainer);
        textRecommendation = findViewById(R.id.textRecommendation);

        setupBarChartWithDB();
    }

    private void setupBarChartWithDB() {
        Map<Integer, Float> sleepMap = new HashMap<>();

        // 초기화 (월~일, 0~6)
        for (int i = 0; i < 7; i++) {
            sleepMap.put(i, 0f);
        }

        SleepDatabaseHelper dbHelper = new SleepDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT date, sleep_time, wake_time FROM SleepRecords", null);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        while (cursor.moveToNext()) {
            try {
                String dateStr = cursor.getString(0);
                String sleepTimeStr = cursor.getString(1);
                String wakeTimeStr = cursor.getString(2);

                Date date = sdf.parse(dateStr);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 일:1 ~ 토:7
                int dayIndex = (dayOfWeek + 5) % 7; // 월:0 ~ 일:6

                Date sleepTime = timeFormat.parse(sleepTimeStr);
                Date wakeTime = timeFormat.parse(wakeTimeStr);

                long diff = wakeTime.getTime() - sleepTime.getTime();
                if (diff < 0) diff += 24 * 60 * 60 * 1000; // 자정 넘김 고려

                float hours = diff / (1000f * 60f * 60f);
                float current = sleepMap.get(dayIndex);
                sleepMap.put(dayIndex, current + hours);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        cursor.close();

        ArrayList<BarEntry> entries = new ArrayList<>();
        float total = 0f;
        for (int i = 0; i < 7; i++) {
            float value = sleepMap.get(i);
            entries.add(new BarEntry(i, value));
            total += value;
        }

        float average = total / 7f;
        textAverage.setText(String.format(Locale.getDefault(), "평균 수면 시간: %.1f시간", average));


        BarDataSet dataSet = new BarDataSet(entries, "수면 시간 (시간)");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                switch ((int) value) {
                    case 0: return "월";
                    case 1: return "화";
                    case 2: return "수";
                    case 3: return "목";
                    case 4: return "금";
                    case 5: return "토";
                    case 6: return "일";
                    default: return "";
                }
            }
        });

        barChart.invalidate();

        // 추천 문구 출력
        if (average < 6) {
            textRecommendation.setText("수면 시간이 부족해요. 일찍 자는 습관을 들여보세요.");
        } else if (average < 7) {
            textRecommendation.setText("약간 부족한 수면입니다. 7시간 이상 자는 것을 추천드려요.");
        } else {
            textRecommendation.setText("좋은 수면 패턴을 유지하고 있어요! 계속 유지해보세요.");
        }
    }
}
