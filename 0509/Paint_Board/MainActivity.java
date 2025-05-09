package com.example.paint_board;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCanvasView canvasView;
    private SeekBar colorSeekBar;
    private SeekBar strokeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MyCanvasView 초기화
        canvasView = findViewById(R.id.myCanvas);

        // 색상 SeekBar 초기화
        colorSeekBar = findViewById(R.id.colorSeekBar);
        colorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                canvasView.setColor(progress);  // 색상 변경
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 굵기 SeekBar 초기화
        strokeSeekBar = findViewById(R.id.strokeSeekBar);
        strokeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                canvasView.setStrokeWidth(progress);  // 선 굵기 변경
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 지우기 버튼
        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.clear();  // 캔버스 지우기
            }
        });
    }
}
