package com.example.calculator4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num1EditText, num2EditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1EditText = findViewById(R.id.editTextNumber1);
        num2EditText = findViewById(R.id.editTextNumber2);
        resultTextView = findViewById(R.id.textViewResult);

        setOperator(R.id.btnAdd, "+");
        setOperator(R.id.btnSub, "-");
        setOperator(R.id.btnMul, "*");
        setOperator(R.id.btnDiv, "/");
    }

    private void setOperator(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            String input1 = num1EditText.getText().toString();
            String input2 = num2EditText.getText().toString();

            if (input1.isEmpty() || input2.isEmpty()) {
                resultTextView.setText("숫자를 입력하세요.");
                return;
            }

            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);
            double result = 0;

            switch (op) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 == 0) {
                        resultTextView.setText("0으로 나눌 수 없습니다.");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            resultTextView.setText("결과: " + result);
        });
    }
}
