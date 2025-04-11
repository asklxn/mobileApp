package com.example.dice;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dice.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewDice;
    private Button buttonRoll;
    private final Random random = new Random();
    private final int[] diceImages = {
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
    };

    private final Handler handler = new Handler();
    private int rollCount = 0;
    private final int maxRolls = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewDice = findViewById(R.id.imageViewDice);
        buttonRoll = findViewById(R.id.buttonRoll);

        buttonRoll.setOnClickListener(v -> startRolling());
    }

    private void startRolling() {
        rollCount = 0;
        buttonRoll.setEnabled(false);
        handler.post(rollRunnable);
    }

    private final Runnable rollRunnable = new Runnable() {
        @Override
        public void run() {
            int index = random.nextInt(6);
            imageViewDice.setImageResource(diceImages[index]);
            rollCount++;

            if (rollCount < maxRolls) {
                handler.postDelayed(this, 100); // 100ms 간격으로 주사위 눈 바꾸기
            } else {
                buttonRoll.setEnabled(true); // 롤 끝나면 버튼 다시 활성화
            }
        }
    };
}
