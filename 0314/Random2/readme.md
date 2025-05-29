# Random

이 앱은 버튼 클릭 시 0부터 99까지의 정수 중 하나를 무작위로 생성하여 화면에 표시해주는 간단한 안드로이드 예제 앱입니다.

## 📱 주요 기능

- 버튼 클릭 시 난수 생성
- 생성된 난수를 `TextView`에 출력

## 🔧 사용 기술

- Java  
- Android Studio  
- `Random` 클래스를 통한 난수 생성  
- XML 기반 레이아웃  
- `EdgeToEdge` 전체화면 UI 처리

## 📂 파일 구조

app/
├── java/
│ └── com.example.random/
│ └── MainActivity.java
├── res/
│ ├── layout/
│ │ └── activity_main.xml
│ └── values/
│ └── strings.xml

## 🧠 코드 설명

### 난수 생성 및 출력

```java
public void generateRandomNumber(View view) {
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    textViewRandomNumber.setText("난수: " + randomNumber);
}
Random 객체를 통해 0~99 사이의 난수를 생성합니다.

생성된 숫자를 TextView에 출력합니다.

전체 화면 UI 처리

EdgeToEdge.enable(this);
시스템 바와 겹치지 않도록 전체 화면 UI 활성화

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
상태 바 및 내비게이션 바의 여백을 자동으로 감지하여 적절한 패딩을 적용합니다.

✅ 사용 방법
앱을 실행합니다.

화면의 버튼을 누르면 무작위 정수가 생성되어 표시됩니다.

🤝 기여
이 프로젝트는 안드로이드에서 난수 생성 기능을 연습하기 위한 학습용 예제입니다.
자유롭게 포크하거나 확장하여 사용하실 수 있습니다.
