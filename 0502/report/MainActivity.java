package com.example.report;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox[] checkBoxes = new CheckBox[10];
    Button submitBtn;
    TextView resultText;
    LinearLayout surveyLayout, resultLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxes[0] = findViewById(R.id.q1);
        checkBoxes[1] = findViewById(R.id.q2);
        checkBoxes[2] = findViewById(R.id.q3);
        checkBoxes[3] = findViewById(R.id.q4);
        checkBoxes[4] = findViewById(R.id.q5);
        checkBoxes[5] = findViewById(R.id.q6);
        checkBoxes[6] = findViewById(R.id.q7);
        checkBoxes[7] = findViewById(R.id.q8);
        checkBoxes[8] = findViewById(R.id.q9);
        checkBoxes[9] = findViewById(R.id.q10);

        surveyLayout = findViewById(R.id.surveyLayout);
        resultLayout = findViewById(R.id.resultLayout);
        resultText = findViewById(R.id.resultText);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(view -> {
            int yesCount = 0;
            for (CheckBox cb : checkBoxes) {
                if (cb.isChecked()) yesCount++;
            }

            String message;
            if (yesCount >= 9) {
                message = "아주 건강한 생활 습관을 유지하고 있군요! 👍";
            } else if (yesCount >= 6) {
                message = "꽤 좋은 편이에요. 조금만 더 노력해봐요!";
            } else if (yesCount >= 3) {
                message = "조금 더 신경 쓸 필요가 있어요. 건강은 소중하니까요!";
            } else {
                message = "생활 습관 개선이 절실해 보입니다. 지금부터 시작해보세요!";
            }

            surveyLayout.setVisibility(View.GONE);
            resultLayout.setVisibility(View.VISIBLE);
            resultText.setText("예를 선택한 항목 수: " + yesCount + "개\n\n" + message);
        });
    }
}