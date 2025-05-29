# Option Menu Sample App

## 소개
이 앱은 옵션 메뉴를 통해 화면 배경색을 변경할 수 있는 간단한 Android 앱입니다.  
옵션 메뉴는 액션바 또는 메뉴 버튼을 눌러 열 수 있으며, 사용자에게 여러 선택지를 제공합니다.

## 주요 기능
- 옵션 메뉴에 배경색 선택 항목 추가 (Blue, Green, Red)
- 메뉴 선택 시 화면 배경색 변경

## 사용법
1. 앱 실행 후 메뉴 버튼을 누르거나 액션바 메뉴를 엽니다.
2. "Blue", "Green", "Red" 중 하나를 선택합니다.
3. 선택한 색상으로 화면 배경색이 변경됩니다.

---

## 주요 코드 설명

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.mymenu, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.blue) {
        view1.setBackgroundColor(Color.BLUE);
        return true;
    } else if (id == R.id.green) {
        view1.setBackgroundColor(Color.GREEN);
        return true;
    } else if (id == R.id.red) {
        view1.setBackgroundColor(Color.RED);
        return true;
    }
    return super.onOptionsItemSelected(item);
}

