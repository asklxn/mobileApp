package com.example.todo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        textViewDate = findViewById(R.id.textViewDate);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        adapter = new TodoAdapter(this, itemList);
        listView.setAdapter(adapter);

        // 날짜 선택 버튼 클릭
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
                itemList.add(new TodoItem(task, selectedDate));
                adapter.notifyDataSetChanged();
                editTextTask.setText("");
                textViewDate.setText("");
                selectedDate = "";
            } else {
                Toast.makeText(MainActivity.this, "할 일과 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
