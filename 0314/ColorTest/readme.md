# ColorTest

이 앱은 `EdgeToEdge` 디자인을 적용한 기본 안드로이드 앱 구조입니다. 상태 바 및 내비게이션 바 여백 처리를 통해 콘텐츠가 전체 화면에 자연스럽게 배치되도록 구성되었습니다.

## 📱 주요 기능

- 시스템 바 여백 자동 처리 (`EdgeToEdge`)  
- XML 레이아웃 파일 `activity_main.xml`을 기본 화면으로 사용

## 🔧 사용 기술

- Java  
- Android Studio  
- `androidx.core`의 `EdgeToEdge` 및 `WindowInsetsCompat` API 사용  
- XML 레이아웃

## 📂 파일 구조

app/
├── java/
│ └── com.example.colortest/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 🧠 코드 설명

### 시스템 바 여백 처리

```java
EdgeToEdge.enable(this);
콘텐츠가 상태 바 및 내비게이션 바와 겹치지 않도록 전체 화면 UI 설정을 활성화합니다.

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
시스템 바 영역(상단/하단)의 여백을 자동으로 감지하여 해당 뷰에 적절한 패딩을 부여합니다.

💡 기타
기본 구조를 바탕으로 이후 다양한 UI 요소(버튼, 텍스트, 색상 등)를 추가해 실습할 수 있는 프로젝트입니다.

🤝 기여
이 프로젝트는 안드로이드 UI 디자인 학습용으로 제작되었습니다.
자유롭게 수정하여 사용하실 수 있습니다.
