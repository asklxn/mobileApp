package com.example.memo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MemoAdapter.OnMemoDeleteListener {

    private EditText editTextNote;
    private Button buttonAdd;
    private Button buttonPickDate;
    private RecyclerView recyclerView;

    private ArrayList<Memo> memoList;
    private MemoAdapter memoAdapter;

    private String selectedDate = "";

    private final String FILE_NAME = "memo_data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        recyclerView = findViewById(R.id.recyclerView);

        memoList = new ArrayList<>();
        memoAdapter = new MemoAdapter(memoList, this);  // 삭제 리스너 넘김

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(memoAdapter);

        loadMemosFromFile();

        // 초기 날짜를 오늘 날짜로 세팅
        Calendar cal = Calendar.getInstance();
        selectedDate = String.format("%04d-%02d-%02d",
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        buttonPickDate.setText(selectedDate);

        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                                buttonPickDate.setText(selectedDate);
                            }
                        },
                        now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = editTextNote.getText().toString().trim();
                if (!note.isEmpty()) {
                    Memo newMemo = new Memo(note, selectedDate);
                    memoList.add(0, newMemo);
                    memoAdapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                    editTextNote.setText("");
                    saveMemosToFile();
                }
            }
        });
    }

    private void saveMemosToFile() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            for (Memo memo : memoList) {
                writer.write(memo.getDate() + "|" + memo.getText());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMemosFromFile() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length == 2) {
                    Memo memo = new Memo(parts[1], parts[0]);
                    memoList.add(memo);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MemoAdapter.OnMemoDeleteListener 구현
    @Override
    public void onMemoDelete(int position) {
        if (position >= 0 && position < memoList.size()) {
            memoList.remove(position);
            memoAdapter.notifyItemRemoved(position);
            saveMemosToFile();
        }
    }
}
