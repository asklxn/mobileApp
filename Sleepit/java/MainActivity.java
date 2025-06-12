package com.example.sleepit;


import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // 변수 선언들...
    TextView textDate, textGoalTime, textQualityScore, textSleepTime, textWakeTime, textSelectedDate;
    Button buttonGoToRecord, buttonGoToGraph, buttonGoToAlarm, buttonSaveRecord, buttonPickDate;
    SleepDatabaseHelper dbHelper;
    Calendar selectedCalendar = Calendar.getInstance();
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSelectedDate = findViewById(R.id.textSelectedDate);
        dbHelper = new SleepDatabaseHelper(this);

        // findViewById 연결

        buttonPickDate = findViewById(R.id.buttonPickDate);
        textGoalTime = findViewById(R.id.textGoalTime);
        textQualityScore = findViewById(R.id.textQualityScore);
        textSleepTime = findViewById(R.id.textSleepTime);
        textWakeTime = findViewById(R.id.textWakeTime);
        buttonGoToGraph = findViewById(R.id.buttonGoToGraph);
        buttonGoToAlarm = findViewById(R.id.buttonGoToAlarm);
        buttonSaveRecord = findViewById(R.id.buttonSaveRecord);

        Button buttonPickSleepTime = findViewById(R.id.buttonPickSleepTime);
        Button buttonPickWakeTime = findViewById(R.id.buttonPickWakeTime);

        buttonPickSleepTime.setOnClickListener(v -> showTimePicker(true));
        buttonPickWakeTime.setOnClickListener(v -> showTimePicker(false));

        // 카드 클릭 리스너
        textSleepTime.setOnClickListener(v -> showTimePicker(true));
        textWakeTime.setOnClickListener(v -> showTimePicker(false));

        // 날짜 표시
        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Calendar.getInstance().getTime());
        if (textDate != null) {
            textDate.setText("오늘 날짜: " + today);
        }

        // 예시 표시
        textGoalTime.setText("8시간 30분");
        textQualityScore.setText("수면 품질 점수: 95점");
        textSleepTime.setText("오후 11:00");
        textWakeTime.setText("오전 7:30");

        // 버튼 기능
        buttonGoToGraph.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            startActivity(intent);
        });

        buttonGoToAlarm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
        });

        buttonPickDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            new android.app.DatePickerDialog(this, (view, y, m, d) -> {
                String selected = String.format(Locale.getDefault(), "%04d-%02d-%02d", y, m + 1, d);
                textSelectedDate.setText("선택 날짜: " + selected);
            }, year, month, day).show();
        });


        buttonSaveRecord.setOnClickListener(v -> {
            String sleepTime = textSleepTime.getText().toString();
            String wakeTime = textWakeTime.getText().toString();
            String selectedDate = textSelectedDate.getText().toString().replace("선택 날짜: ", "").trim();


            if (sleepTime.isEmpty() || wakeTime.isEmpty()) {
                Toast.makeText(this, "취침 시간과 기상 시간을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "날짜를 선택하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            String formattedSleep = convertTo24Hour(sleepTime);
            String formattedWake = convertTo24Hour(wakeTime);

            if (formattedSleep == null || formattedWake == null) {
                Toast.makeText(this, "시간 형식 오류입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // ✅ 해당 날짜의 기존 기록 제거
            db.delete("SleepRecords", "date = ?", new String[]{selectedDate});

            // ✅ 새 기록 삽입
            ContentValues values = new ContentValues();
            values.put("date", selectedDate);
            values.put("sleep_time", formattedSleep);
            values.put("wake_time", formattedWake);

            long newRowId = db.insert("SleepRecords", null, values);
            if (newRowId != -1) {
                Toast.makeText(this, "수면 기록 저장됨", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
            }
        });



    }

    // ⬇️⬇️⬇️ 여기 onCreate 밖에 둬야 함 ⬇️⬇️⬇️
    private void showTimePicker(boolean isSleepTime) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            Calendar selectedTime = Calendar.getInstance();
            selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
            selectedTime.set(Calendar.MINUTE, selectedMinute);

            SimpleDateFormat koreanFormat = new SimpleDateFormat("a hh:mm", Locale.KOREA);
            String formatted = koreanFormat.format(selectedTime.getTime())
                    .replace("AM", "오전")
                    .replace("PM", "오후");

            if (isSleepTime) {
                // 취침 시간 선택됨 → 기상 시간은 +8시간 30분
                textSleepTime.setText(formatted);

                Calendar wakeTime = (Calendar) selectedTime.clone();
                wakeTime.add(Calendar.HOUR_OF_DAY, 8);
                wakeTime.add(Calendar.MINUTE, 30);
                String wakeFormatted = koreanFormat.format(wakeTime.getTime())
                        .replace("AM", "오전").replace("PM", "오후");
                textWakeTime.setText(wakeFormatted);

            } else {
                // 기상 시간 선택됨 → 취침 시간은 -8시간 30분
                textWakeTime.setText(formatted);

                Calendar sleepTime = (Calendar) selectedTime.clone();
                sleepTime.add(Calendar.HOUR_OF_DAY, -8);
                sleepTime.add(Calendar.MINUTE, -30);
                String sleepFormatted = koreanFormat.format(sleepTime.getTime())
                        .replace("AM", "오전").replace("PM", "오후");
                textSleepTime.setText(sleepFormatted);
            }

        }, hour, minute, false);

        dialog.show();
    }


    private String convertTo24Hour(String amPmTime) {
        try {
            SimpleDateFormat koreanFormat = new SimpleDateFormat("a hh:mm", Locale.KOREA);
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm", Locale.KOREA);
            return hourFormat.format(koreanFormat.parse(amPmTime));
        } catch (Exception e) {
            e.printStackTrace(); // 👈 디버깅용 로그
            return null; // ⚠️ 이렇게 null이 리턴되면 insert 실패
        }
    }


}

