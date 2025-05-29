# TwoButton

`TwoButton` 앱은 두 개의 버튼을 세로로 배치한 간단한 안드로이드 앱입니다.  
아직 버튼 클릭 시 동작은 구현되어 있지 않으며, 기본 UI 구성 예제입니다.

## 주요 기능

- 세로 방향으로 배치된 두 개의 버튼  
- 각 버튼에는 각각 "첫 번째 버튼", "두 번째 버튼" 텍스트 표시  

## 레이아웃 설명 (`activity_main.xml`)

```xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="첫 번째 버튼"/>

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="두 번째 버튼"/>

</LinearLayout>
향후 개선점
버튼 클릭 이벤트 처리 추가

UI 스타일링 및 기능 확장

기여
간단한 UI 학습용 프로젝트이며, 자유롭게 수정 및 개선 가능합니다.
