# ColorTest

이 앱은 초록색 배경과 버튼 1개를 가진 간단한 안드로이드 앱입니다.  
`EdgeToEdge` UI를 적용하여 시스템 바와 겹치지 않는 전체 화면 레이아웃을 구현했습니다.

## 주요 기능

- `EdgeToEdge` 전체 화면 UI 적용  
- 시스템 바 영역 자동 여백 처리  
- 초록색 배경을 가진 세로 방향 `LinearLayout`  
- 기본 버튼 1개 표시

## 사용 기술

- Java (MainActivity)  
- Android Studio  
- `androidx.core` 라이브러리의 `EdgeToEdge`, `WindowInsetsCompat` 활용  
- XML 기반 레이아웃

## 파일 구조

app/
├── java/
│ └── com.example.colortest/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

xml
복사
편집

## 레이아웃 설명 (`activity_main.xml`)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#48c34a">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="버튼" />

</LinearLayout>
LinearLayout 배경색은 초록색(#48c34a)

세로 방향(vertical)

버튼 1개 텍스트는 "버튼"

기여
이 프로젝트는 안드로이드 UI 및 EdgeToEdge 기능 학습용 예제입니다.
필요에 따라 자유롭게 수정 및 확장 가능합니다.
