package com.example.chellenge;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eText1, eText2, eText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditText 초기화
        eText1 = findViewById(R.id.edit1);
        eText2 = findViewById(R.id.edit2);
        eText3 = findViewById(R.id.edit3);
    }

    // 덧셈 버튼 클릭 시 호출되는 메서드
    public void cal_plus(View v) {
        try {
            // 입력값 가져오기
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();

            // 입력값이 비어있는지 확인
            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // 숫자로 변환
            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = num1 + num2;  // 덧셈 연산

            // 결과를 EditText에 출력
            eText3.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    // 뺄셈 버튼 클릭 시 호출되는 메서드
    public void cal_minus(View v) {
        try {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();

            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = num1 - num2;  // 뺄셈 연산

            eText3.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    // 곱셈 버튼 클릭 시 호출되는 메서드
    public void cal_multiply(View v) {
        try {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();

            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = num1 * num2;  // 곱셈 연산

            eText3.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    // 나눗셈 버튼 클릭 시 호출되는 메서드
    public void cal_divide(View v) {
        try {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();

            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);

            if (num2 == 0) {
                Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double result = num1 / num2;  // 나눗셈 연산

            eText3.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }
}
