# Egg Timer 앱

## 소개
Egg Timer 앱은 사용자가 설정한 시간 동안 카운트다운을 진행하고, 시간이 완료되면 알림을 보내는 간단한 타이머 애플리케이션입니다.  
타이머가 종료되면 알림(Notification)이 표시되고, 사용자에게 10초 연장 여부를 묻는 다이얼로그가 나타납니다.

---

## 주요 기능
- 10초 카운트다운 타이머 실행
- 타이머 종료 시 알림(Notification) 전송
- 알림 클릭 시 구글 홈페이지로 이동
- 타이머 종료 후 10초 연장 여부를 묻는 다이얼로그 표시
- 연장 선택 시 타이머 재시작, 종료 선택 시 앱 완전 종료

---

## 사용법
1. 앱 실행 후 타이머 시작 버튼을 누릅니다.
2. 카운트다운이 1초 단위로 진행되며, 남은 시간이 화면에 표시됩니다.
3. 타이머가 종료되면 알림이 표시되고, 10초 연장 여부를 묻는 다이얼로그가 뜹니다.
4. "연장"을 누르면 타이머가 다시 시작되고, "종료"를 누르면 앱이 종료됩니다.

---

## 주요 코드

```java
public void startTimer(View view) {
    new CountDownTimer(10 * 1000, 1000) {
        public void onTick(long millisUntilFinished) {
            mEditText.setText((millisUntilFinished / 1000) + "초");
        }

        public void onFinish() {
            mEditText.setText("done!");
            sendNotification();
            showExtendDialog(); // 타이머 종료 후 다이얼로그 표시
        }
    }.start();
}

