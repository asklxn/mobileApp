# Claude Menu App

## 소개
Claude Menu 앱은 사용자가 텍스트뷰를 길게 눌러 컨텍스트 메뉴를 통해 배경색을 변경할 수 있는 간단한 Android 애플리케이션입니다.

## 주요 기능
- 텍스트뷰에 컨텍스트 메뉴 등록
- 메뉴에서 선택한 색상으로 배경색 변경 (빨간색, 녹색, 파란색, 노란색, 기본색)
- 배경색 변경 시 토스트 메시지로 사용자에게 알림 제공

## 구현 상세
- `ConstraintLayout`을 배경 레이아웃으로 사용
- `registerForContextMenu()`를 통해 텍스트뷰에 컨텍스트 메뉴 등록
- `onCreateContextMenu()`에서 메뉴 항목 생성 및 제목 설정
- `onContextItemSelected()`에서 메뉴 선택에 따라 배경색 변경 및 토스트 출력

## 사용 방법
1. 앱 실행 후 화면 중앙의 텍스트뷰를 길게 누릅니다.
2. 나타난 메뉴에서 원하는 배경색을 선택합니다.
3. 선택한 색상으로 화면 배경이 즉시 변경되고, 변경 사실을 토스트로 확인할 수 있습니다.

---
### 코드 주요 부분

```java
@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    menu.setHeaderTitle("배경색 선택");
    menu.add(0, 1, 0, "빨간색");
    menu.add(0, 2, 0, "녹색");
    menu.add(0, 3, 0, "파란색");
    menu.add(0, 4, 0, "노란색");
    menu.add(0, 5, 0, "기본색");
}

@Override
public boolean onContextItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
        case 1:
            mainLayout.setBackgroundColor(Color.RED);
            Toast.makeText(this, "빨간색으로 변경되었습니다", Toast.LENGTH_SHORT).show();
            return true;
        // 기타 색상 처리...
    }
    return super.onContextItemSelected(item);
}

