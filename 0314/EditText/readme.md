# EditTextTest

이 앱은 안드로이드에서 텍스트 입력을 위한 `EditText` 기능을 테스트하고 학습하기 위한 기본 앱입니다. 최신 안드로이드 디자인인 `EdgeToEdge`를 적용하여 시스템 UI 영역을 고려한 전체 화면 처리가 되어 있습니다.

## 📱 주요 기능

- 텍스트 입력 필드 (`EditText`) 사용  
- 시스템 바 영역 자동 처리 (`EdgeToEdge`)

## 🔧 사용 기술

- Java  
- Android Studio  
- `androidx.core`의 `EdgeToEdge` 및 `WindowInsetsCompat` API  
- XML 기반 레이아웃

## 📂 파일 구조

app/
├── java/
│ └── com.example.edittexttest/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 🧠 코드 설명

### 시스템 바 여백 자동 처리

```java
EdgeToEdge.enable(this);
전체 화면을 사용하는 UI 설정 활성화

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
상태 바 및 내비게이션 바 영역을 자동으로 감지하여 적절한 여백을 적용

💡 추가 예정 기능
사용자 입력값 처리 및 출력

입력 유효성 검사 기능

버튼과 연동하여 동적 처리

🤝 기여
이 프로젝트는 안드로이드 EditText 학습을 위한 예제 앱입니다.
자유롭게 포크하거나 수정해서 사용하실 수 있습니다.
