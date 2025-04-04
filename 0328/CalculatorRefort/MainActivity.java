package com.example.calculatorrefort;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "0";
    private String operator = "";
    private double firstOperand = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textViewDisplay);
        display.setText("0");

        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.btnDot
        };

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String digit = b.getText().toString();

                if (isNewOperation) {
                    currentInput = "";
                    isNewOperation = false;
                }

                if (digit.equals(".") && currentInput.contains(".")) return;
                if (currentInput.equals("0") && !digit.equals(".")) {
                    currentInput = digit;
                } else {
                    currentInput += digit;
                }

                display.setText(currentInput);
            }
        };

        for (int id : numberButtonIds) {
            Button btn = findViewById(id);
            if (btn != null) btn.setOnClickListener(numberListener);
        }

        setOperator(R.id.btnAdd, "+");
        setOperator(R.id.btnSub, "-");
        setOperator(R.id.btnMul, "x");
        setOperator(R.id.btnDiv, "/");

        Button equal = findViewById(R.id.btnEqual);
        equal.setOnClickListener(v -> calculateResult());

        Button clear = findViewById(R.id.btnClear);
        clear.setOnClickListener(v -> {
            currentInput = "0";
            firstOperand = 0;
            operator = "";
            isNewOperation = true;
            display.setText("0");
        });

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            if (currentInput.length() > 1) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
            } else {
                currentInput = "0";
            }
            display.setText(currentInput);
        });
    }

    private void setOperator(int buttonId, String op) {
        Button btn = findViewById(buttonId);
        btn.setOnClickListener(v -> {
            try {
                firstOperand = Double.parseDouble(currentInput);
            } catch (NumberFormatException e) {
                firstOperand = 0;
            }
            operator = op;
            isNewOperation = true;
        });
    }

    private void calculateResult() {
        try {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "x":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand == 0) {
                        display.setText("Error");
                        return;
                    }
                    result = firstOperand / secondOperand;
                    break;
            }

            currentInput = String.valueOf(result);
            display.setText(currentInput);
            isNewOperation = true;
        } catch (Exception e) {
            display.setText("Error");
            currentInput = "0";
        }
    }
}
