package com.example.memo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditMemoActivity extends AppCompatActivity {

    private EditText editTitle, editContent;
    private Button btnSave, btnDelete;

    private String filename = null; // 기존 메모 파일명 받음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        filename = getIntent().getStringExtra("filename");

        if (filename != null) {
            btnDelete.setVisibility(View.VISIBLE);
            loadMemo(filename);
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(v -> saveMemo());
        btnDelete.setOnClickListener(v -> deleteMemo());
    }

    private void saveMemo() {
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        long timestamp = System.currentTimeMillis();

        if (filename == null) {
            filename = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    .format(new Date()) + ".txt";
        }

        try {
            File file = new File(getFilesDir(), filename);
            FileWriter writer = new FileWriter(file, false);
            writer.write(title + "\n");
            writer.write(content + "\n");
            writer.write(String.valueOf(timestamp));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }

    private void deleteMemo() {
        if (filename != null) {
            File file = new File(getFilesDir(), filename);
            if (file.exists()) {
                file.delete();
            }
        }
        finish();
    }

    private void loadMemo(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            String title = reader.readLine();
            String content = reader.readLine();
            reader.close();

            editTitle.setText(title);
            editContent.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
