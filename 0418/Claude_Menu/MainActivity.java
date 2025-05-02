// MainActivity.java
package com.example.claude_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃과 텍스트뷰 초기화
        mainLayout = findViewById(R.id.main_layout);
        textView = findViewById(R.id.textView);

        // 텍스트뷰에 컨텍스트 메뉴 등록
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // 메뉴 제목 설정
        menu.setHeaderTitle("배경색 선택");

        // 메뉴 항목 추가
        menu.add(0, 1, 0, "빨간색");
        menu.add(0, 2, 0, "녹색");
        menu.add(0, 3, 0, "파란색");
        menu.add(0, 4, 0, "노란색");
        menu.add(0, 5, 0, "기본색");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // 선택된 메뉴 항목에 따라 배경색 변경
        switch (item.getItemId()) {
            case 1: // 빨간색
                mainLayout.setBackgroundColor(Color.RED);
                Toast.makeText(this, "빨간색으로 변경되었습니다", Toast.LENGTH_SHORT).show();
                return true;
            case 2: // 녹색
                mainLayout.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "녹색으로 변경되었습니다", Toast.LENGTH_SHORT).show();
                return true;
            case 3: // 파란색
                mainLayout.setBackgroundColor(Color.BLUE);
                Toast.makeText(this, "파란색으로 변경되었습니다", Toast.LENGTH_SHORT).show();
                return true;
            case 4: // 노란색
                mainLayout.setBackgroundColor(Color.YELLOW);
                Toast.makeText(this, "노란색으로 변경되었습니다", Toast.LENGTH_SHORT).show();
                return true;
            case 5: // 기본색
                mainLayout.setBackgroundColor(Color.WHITE);
                Toast.makeText(this, "기본색으로 변경되었습니다", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}