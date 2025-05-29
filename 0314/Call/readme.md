# MyApplication - 전화 다이얼 실행 앱

이 앱은 버튼 클릭 시 기본 전화 앱을 실행하여 지정된 번호로 전화를 걸 수 있도록 합니다. 최신 안드로이드 디자인을 적용하여 시스템 바 여백을 자동으로 조절하는 `EdgeToEdge` UI도 포함되어 있습니다.

## 📱 주요 기능

- 버튼 클릭 시 전화 다이얼 앱 실행  
- `tel:` 스킴을 사용한 전화 Intent 호출  
- 시스템 바 여백 자동 조정 (EdgeToEdge 적용)

## 🔧 사용 기술

- Java  
- Android Studio  
- Intent (`ACTION_VIEW`, `tel:`)  
- `EdgeToEdge` API (`androidx.core`)  
- XML 레이아웃

## 📂 파일 구조

app/
├── java/
│ └── com.example.myapplication/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 🚀 실행 방법

1. Android Studio에서 프로젝트 열기  
2. 에뮬레이터 또는 실제 기기에서 앱 실행  
3. 버튼 클릭 시 전화 앱이 열리며 `010-1234-1234` 번호로 연결 준비

> ⚠️ **실제 전화가 걸리지 않으며, 다이얼 화면만 표시됩니다.**  
> ⚠️ **실제 기기에서 테스트 시 권한 문제가 발생하지 않도록 `AndroidManifest.xml`에 권한 설정 필요 없음 (ACTION_VIEW + tel 스킴은 권한 없이 가능)**

## 🧠 코드 설명

### 전화 Intent 실행

```java
public void onClicked(View v) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-1234"));
    startActivity(intent);
}
Intent.ACTION_VIEW와 "tel:..." URI를 사용하여 다이얼러 앱을 실행합니다.

시스템 바 여백 조정

EdgeToEdge.enable(this);

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
EdgeToEdge 레이아웃을 적용하여 상태 바, 내비게이션 바 영역을 자동으로 감지하고 패딩을 적용합니다.

🤝 기여
이 프로젝트는 안드로이드 앱 개발 학습용으로 제작되었습니다.
자유롭게 포크하거나 수정하여 사용하실 수 있습니다.
