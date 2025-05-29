# To-Do List 앱

## 개요
간단한 할 일 관리 앱입니다.  
할 일을 입력하고, 날짜를 지정한 후 리스트에 추가할 수 있습니다.  
SQLite 데이터베이스를 사용해 할 일을 저장하고 삭제할 수 있습니다.

## 주요 기능
- 할 일 추가 (내용 + 날짜 지정)  
- 할 일 목록 조회  
- 할 일 삭제  
- 날짜 선택을 위한 DatePicker 지원  

## 사용 기술
- Android SDK  
- SQLite 데이터베이스  
- ListView와 커스텀 Adapter  

## 파일 구성
- `MainActivity.java`: 메인 화면 및 DB 연동 구현  
- `TodoDbHelper.java`: SQLite 데이터베이스 헬퍼 클래스  
- `TodoAdapter.java`: ListView용 커스텀 어댑터  
- `activity_main.xml`: UI 레이아웃 파일  

## 데이터베이스
- 테이블명: `todo_table`  
- 주요 컬럼: `id`, `task`, `date`  

## 실행 방법
1. 앱 실행 후 할 일과 날짜를 입력합니다.  
2. 추가 버튼을 눌러 할 일을 등록합니다.  
3. 등록된 할 일은 리스트에 표시됩니다.  
4. 삭제 버튼으로 할 일을 삭제할 수 있습니다.

