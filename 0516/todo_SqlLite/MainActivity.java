package com.example.todo;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextTask;
    TextView textViewDate;
    Button buttonAdd, buttonPickDate;
    ListView listView;

    ArrayList<TodoItem> itemList;
    TodoAdapter adapter;

    String selectedDate = "";

    TodoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TodoDbHelper(this);

        editTextTask = findViewById(R.id.editTextTask);
        textViewDate = findViewById(R.id.textViewDate);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        adapter = new TodoAdapter(this, itemList);
        listView.setAdapter(adapter);

        // DB에서 할 일 리스트 불러오기
        loadItemsFromDb();

        adapter.setOnDeleteListener(position -> {
            TodoItem item = itemList.get(position);
            deleteItemFromDb(item);
            loadItemsFromDb();
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

            if (!task.isEmpty() && !selectedDate.isEmpty()) {
                addItemToDb(task, selectedDate);
                editTextTask.setText("");
                textViewDate.setText("");
                selectedDate = "";
                loadItemsFromDb(); // DB에서 다시 불러와 갱신
            } else {
                Toast.makeText(MainActivity.this, "할 일과 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        });
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

            itemList.add(new TodoItem(task, date));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void addItemToDb(String task, String date) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String insertQuery = "INSERT INTO " + TodoDbHelper.TABLE_NAME + " (" +
                TodoDbHelper.COL_TASK + ", " + TodoDbHelper.COL_DATE + ") VALUES (?, ?)";

        db.execSQL(insertQuery, new Object[]{task, date});
    }

    private void deleteItemFromDb(TodoItem item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TodoDbHelper.TABLE_NAME + " WHERE " +
                TodoDbHelper.COL_TASK + " = ? AND " + TodoDbHelper.COL_DATE + " = ?";
        db.execSQL(deleteQuery, new Object[]{item.getTask(), item.getDate()});
    }

    // 삭제 기능은 Adapter에서 삭제시 DB에서도 반영하도록 수정 필요
}
