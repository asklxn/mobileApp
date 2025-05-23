package com.example.todo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextTask;
    TextView textViewDate, textViewTime;
    Button buttonAdd, buttonPickDate, buttonPickTime;
    ListView listView;

    private int selectedHour = 0;
    private int selectedMinute = 0;

    ArrayList<TodoItem> itemList;
    TodoAdapter adapter;

    String selectedDate = "";
    String selectedTime = "";
    TodoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TodoDbHelper(this);

        editTextTask = findViewById(R.id.editTextTask);
        textViewDate = findViewById(R.id.textViewDate);
        textViewTime = findViewById(R.id.textViewTime);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        buttonPickTime = findViewById(R.id.buttonPickTime);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        adapter = new TodoAdapter(this, itemList);
        listView.setAdapter(adapter);

        buttonPickTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view, hourOfDay, minute1) -> {
                        selectedHour = hourOfDay;
                        selectedMinute = minute1;
                        selectedTime = String.format("%02d:%02d", hourOfDay, minute1);
                        textViewTime.setText(selectedTime);
                    }, hour, minute, true);
            timePickerDialog.show();
        });

        buttonPickDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, (view1, y, m, d) -> {
                selectedDate = y + "-" + (m + 1) + "-" + d;
                textViewDate.setText(selectedDate);
            }, year, month, day);
            dialog.show();
        });

        buttonAdd.setOnClickListener(view -> {
            String task = editTextTask.getText().toString();

            if (!task.isEmpty() && !selectedDate.isEmpty() && !selectedTime.isEmpty()) {
                addItemToDb(task, selectedDate, selectedTime);
                scheduleAlarm(task, selectedDate, selectedTime);

                editTextTask.setText("");
                textViewDate.setText("");
                textViewTime.setText("");
                selectedDate = "";
                selectedTime = "";
                loadItemsFromDb();
            } else {
                Toast.makeText(MainActivity.this, "할 일과 날짜, 시간을 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnDeleteListener(position -> {
            TodoItem item = itemList.get(position);
            deleteItemFromDb(item);
            loadItemsFromDb();
        });

        loadItemsFromDb(); // 초기 로딩
    }

    private void scheduleAlarm(String task, String date, String time) {
        String[] dateParts = date.split("-");
        String[] timeParts = time.split(":");

        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int day = Integer.parseInt(dateParts[2]);
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("task", task);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (!alarmManager.canScheduleExactAlarms()) {
                Intent alarmIntent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivity(alarmIntent);
                return;
            }
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    private void loadItemsFromDb() {
        itemList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TodoDbHelper.TABLE_NAME,
                null, null, null, null, null,
                TodoDbHelper.COL_ID + " DESC");

        while (cursor.moveToNext()) {
            String task = cursor.getString(cursor.getColumnIndexOrThrow(TodoDbHelper.COL_TASK));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(TodoDbHelper.COL_DATE));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(TodoDbHelper.COL_TIME));
            itemList.add(new TodoItem(task, date, time));
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void addItemToDb(String task, String date, String time) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TodoDbHelper.TABLE_NAME + " (" +
                TodoDbHelper.COL_TASK + ", " + TodoDbHelper.COL_DATE + ", " + TodoDbHelper.COL_TIME + ") VALUES (?, ?, ?)";
        db.execSQL(insertQuery, new Object[]{task, date, time});
    }

    private void deleteItemFromDb(TodoItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TodoDbHelper.TABLE_NAME + " WHERE " +
                TodoDbHelper.COL_TASK + " = ? AND " +
                TodoDbHelper.COL_DATE + " = ? AND " +
                TodoDbHelper.COL_TIME + " = ?";
        db.execSQL(deleteQuery, new Object[]{item.getTask(), item.getDate(), item.getTime()});
    }
}
