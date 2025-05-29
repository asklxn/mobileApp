# Button

두 개의 버튼을 포함한 간단한 안드로이드 앱입니다. `EdgeToEdge` 디자인을 적용하여 최신 UI 트렌드를 반영하며, 버튼 UI 구성과 레이아웃 구조를 이해하는 데 도움이 됩니다.

## 📱 주요 기능

- 두 개의 버튼 UI 구성  
- Edge-to-Edge 레이아웃 적용으로 상태바/내비게이션바 여백 자동 조정

## 🔧 사용 기술

- Java  
- Android Studio  
- XML 레이아웃  
- EdgeToEdge API (`androidx.core`)  
- ViewCompat + WindowInsetsCompat 조합

## 📂 파일 구조

app/
├── java/
│ └── com.example.button/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 🚀 실행 방법

1. Android Studio에서 프로젝트 열기  
2. `MainActivity.java`에서 `activity_main.xml`을 로드  
3. 에뮬레이터 또는 실제 기기에서 실행 후 버튼 UI 확인  

## 🧠 코드 설명

### MainActivity.java

```java
EdgeToEdge.enable(this);
상태바와 내비게이션바를 투명하게 설정하여 콘텐츠가 전체 화면에 표시되도록 함

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
시스템 바의 영역을 감지해 해당 뷰에 패딩을 적용하여 UI 요소가 겹치지 않도록 함

<LinearLayout
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="첫번째 버튼" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="두번째 버튼" />
</LinearLayout>
🤝 기여
이 프로젝트는 안드로이드 앱 개발 학습용으로 제작되었습니다.
자유롭게 포크하거나 확장하여 사용하실 수 있습니다.
