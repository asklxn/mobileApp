package com.example.randomnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int targetNumber;
    private EditText inputGuess;
    private TextView hintText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputGuess = findViewById(R.id.editTextGuess);
        hintText = findViewById(R.id.textViewHint);
        Button guessButton = findViewById(R.id.btnGuess);

        generateRandomNumber();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guessText = inputGuess.getText().toString();

                if (guessText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int guess = Integer.parseInt(guessText);

                if (guess < targetNumber) {
                    hintText.setText("너무 낮아요 (Low)");
                } else if (guess > targetNumber) {
                    hintText.setText("너무 높아요 (High)");
                } else {
                    hintText.setText("정답입니다! 🎉");
                }
            }
        });

    }

    private void generateRandomNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1; // 1 ~ 100
    }
}
