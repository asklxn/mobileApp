package com.example.sleepit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    TextView textAlarmTime, textRecommendedTime, textExpectedSleep, textVibrationLevel;
    Switch switchAlarm;
    SeekBar vibrationSeekBar;
    int hour = 6, minute = 30;
    int vibrationLevel = 2; // 기본 진동 세기 (0~3)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        textAlarmTime = findViewById(R.id.textAlarmTime);
        switchAlarm = findViewById(R.id.switchAlarm);
        textRecommendedTime = findViewById(R.id.textRecommendedTime);
        textExpectedSleep = findViewById(R.id.textExpectedSleep);
        vibrationSeekBar = findViewById(R.id.vibrationSeekBar);
        textVibrationLevel = findViewById(R.id.vibrationLevelText);

        // 초기값 표시
        textAlarmTime.setText(String.format("%02d:%02d", hour, minute));
        textRecommendedTime.setText("오후 11:00 - 11:30");
        textExpectedSleep.setText("7시간 30분");
        updateVibrationText(vibrationLevel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (!alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivity(intent);
            }
        }

        // 시간 선택
        textAlarmTime.setOnClickListener(v -> {
            TimePickerDialog dialog = new TimePickerDialog(this, (view, h, m) -> {
                hour = h;
                minute = m;
                textAlarmTime.setText(String.format("%02d:%02d", hour, minute));
            }, hour, minute, true);
            dialog.show();
        });

        // 진동 세기 SeekBar 설정
        vibrationSeekBar.setMax(3);
        vibrationSeekBar.setProgress(vibrationLevel);
        vibrationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vibrationLevel = progress;
                updateVibrationText(progress);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 알람 on/off
        switchAlarm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setAlarm(hour, minute);
                Toast.makeText(this, "알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
            } else {
                cancelAlarm();
                Toast.makeText(this, "알람이 해제되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateVibrationText(int level) {
        String levelText = switch (level) {
            case 0 -> "없음";
            case 1 -> "약하게";
            case 2 -> "보통";
            case 3 -> "강하게";
            default -> "보통";
        };
        textVibrationLevel.setText("세기: " + levelText);
    }

    private void setAlarm(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("vibrationLevel", vibrationLevel);  // 진동 세기 전달

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.cancel(pendingIntent);
    }
}
