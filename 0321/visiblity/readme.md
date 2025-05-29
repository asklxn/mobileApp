# Visibility App

## 개요
이 앱은 여러 개의 버튼을 배치하고, 각 버튼의 `visibility` 상태를 확인할 수 있는 간단한 안드로이드 앱입니다.  
버튼 클릭 시 해당 버튼의 현재 `visibility` 상태(VISIBLE, INVISIBLE, GONE)를 토스트 메시지로 보여줍니다.

## 기능
- 총 5개의 버튼 배치
- 버튼 2는 기본적으로 `invisible` 상태
- 버튼 4는 기본적으로 `gone` 상태
- 버튼 클릭 시 각 버튼의 현재 상태를 화면에 표시

## 사용법
1. 앱 실행
2. 버튼 클릭
3. 클릭한 버튼의 상태가 토스트로 나타남

## 주요 코드
- `MainActivity.java`에서 각 버튼에 클릭 리스너 설정
- `View.getVisibility()` 메서드를 사용해 버튼 상태 확인

## XML 레이아웃
- LinearLayout 사용
- 각 버튼에 고유 ID 설정 필요

