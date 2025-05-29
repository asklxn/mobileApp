# Challenge

`Challenge` 앱은 기본적인 사칙연산(덧셈, 뺄셈, 곱셈, 나눗셈)을 수행하는 간단한 계산기 앱입니다.  
사용자가 두 숫자를 입력하면 버튼 클릭으로 결과를 계산하여 출력합니다.

## 주요 기능

- 두 개의 입력 필드(EditText)에서 숫자 입력  
- 덧셈, 뺄셈, 곱셈, 나눗셈 버튼으로 계산 수행  
- 결과는 세 번째 EditText에 출력  
- 입력값 검사 및 예외 처리  
  - 빈 입력 경고  
  - 숫자 형식 오류 처리  
  - 0으로 나누기 방지  

## MainActivity.java 주요 코드

```java
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

        eText1 = findViewById(R.id.edit1);
        eText2 = findViewById(R.id.edit2);
        eText3 = findViewById(R.id.edit3);
    }

    public void cal_plus(View v) {
        try {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();

            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = num1 + num2;

            eText3.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    // cal_minus, cal_multiply, cal_divide 메서드도 동일한 방식으로 구현됨
}
기능 설명
cal_plus, cal_minus, cal_multiply, cal_divide 메서드는 각각 버튼 클릭 시 호출됩니다.

입력값이 비어 있거나 숫자가 아닌 경우 토스트 메시지로 안내합니다.

나눗셈 시 0으로 나누는 경우도 토스트 메시지로 처리합니다.

XML 레이아웃
activity_main.xml에는 3개의 EditText와 4개의 버튼이 배치되어 있어야 하며,
각 버튼에 onClick 속성으로 해당 메서드를 지정해야 합니다.

향후 개선점
UI 디자인 개선

추가 연산 기능 구현

입력 필드에 숫자만 입력 가능하도록 제한

결과 출력 UI 개선
