# RandomNumber

이 앱은 버튼을 누르면 0부터 99까지의 난수를 생성하여 화면에 출력하는 간단한 안드로이드 앱입니다.  
`EdgeToEdge` UI를 적용해 시스템 바와 겹치지 않도록 구성했습니다.

## 주요 기능

- `EdgeToEdge` 전체 화면 UI 적용  
- 버튼 클릭 시 0~99 사이 난수 생성 및 출력  
- 난수 출력용 `TextView` 포함

## 사용 기술

- Java  
- Android Studio  
- `androidx.core` 라이브러리의 `EdgeToEdge`, `WindowInsetsCompat` 활용  
- XML 기반 레이아웃

## 파일 구조

app/
├── java/
│ └── com.example.randomnumber/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 코드 설명 (`MainActivity.java`)

```java
package com.example.randomnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textViewRandomNumber = findViewById(R.id.textViewRandomNumber);
    }

    public void generateRandomNumber(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        textViewRandomNumber.setText("난수: " + randomNumber);
    }
}
generateRandomNumber 메서드는 버튼 클릭 시 호출되어 0~99 범위의 난수를 생성하고,
TextView에 "난수: [숫자]" 형식으로 출력합니다.

EdgeToEdge.enable(this)로 상태 바 및 내비게이션 바와 겹치지 않는 UI 구성

예시 레이아웃 (activity_main.xml)

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/textViewRandomNumber"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="난수:"
        android:textSize="24sp"
        android:layout_marginBottom="20dp" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="난수 생성"
        android:onClick="generateRandomNumber" />
</LinearLayout>
기여
이 프로젝트는 안드로이드 UI 및 난수 생성 기능 학습용 예제입니다.
필요에 따라 자유롭게 수정 및 확장 가능합니다.
