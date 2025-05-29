# Challenge

이 앱은 안드로이드에서 `EdgeToEdge` UI를 적용한 기본 구조의 프로젝트입니다.  
시스템 바(상태 바, 내비게이션 바)와 겹치지 않도록 자동으로 여백을 처리합니다.

## 주요 기능

- `EdgeToEdge` 전체 화면 UI 활성화  
- 시스템 바 영역의 여백 자동 적용

## 사용 기술

- Java  
- Android Studio  
- `androidx.core` 라이브러리의 `EdgeToEdge`, `WindowInsetsCompat` 활용

## 파일 구조

app/
├── java/
│ └── com.example.challenge/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 코드 설명

```java
EdgeToEdge.enable(this);
상태 바와 내비게이션 바가 앱 화면과 겹치지 않도록 전체 화면 UI를 활성화합니다.

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
시스템 바의 영역을 감지하여 main 뷰에 적절한 패딩을 적용해 콘텐츠가 가려지지 않도록 합니다.

기여
이 프로젝트는 안드로이드 UI 레이아웃 학습용 예제입니다.
필요에 따라 자유롭게 수정, 확장 가능합니다.
