# Context Menu Sample App

## 소개
이 앱은 텍스트뷰에 컨텍스트 메뉴를 등록하여, 길게 눌렀을 때 배경색을 빨간색, 녹색, 파란색으로 변경할 수 있는 간단한 Android 앱입니다.

## 주요 기능
- 텍스트뷰에 컨텍스트 메뉴 등록
- 컨텍스트 메뉴에서 배경색 선택 (RED, GREEN, BLUE)
- 선택한 색상으로 텍스트뷰 배경색 변경

## 사용법
1. 앱 실행 후 텍스트뷰를 길게 누릅니다.
2. 나타나는 메뉴에서 원하는 배경색을 선택합니다.
3. 텍스트뷰의 배경색이 선택한 색상으로 변경됩니다.

---

## 주요 코드 설명

```java
@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.setHeaderTitle("컨텍스트메뉴");
    menu.add(0, 1, 0, "배경색: RED");
    menu.add(0, 2, 0, "배경색: GREEN");
    menu.add(0, 3, 0, "배경색: BLUE");
}

@Override
public boolean onContextItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == 1) {
        text.setBackgroundColor(Color.RED);
        return true;
    } else if (id == 2) {
        text.setBackgroundColor(Color.GREEN);
        return true;
    } else if (id == 3) {
        text.setBackgroundColor(Color.BLUE);
        return true;
    }
    return super.onContextItemSelected(item);
}

