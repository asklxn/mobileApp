package com.example.complexlayout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ConstraintLoginActivity로 이동
        Intent intent = new Intent(this, ConstraintLoginActivity.class);
        startActivity(intent);

        // 현재 액티비티는 종료해도 되고 안 해도 됨
        finish();
    }
}