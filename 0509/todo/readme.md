# 📋 To_Do: 간단한 할 일 관리 앱 (Java)

사용자가 할 일을 입력하고, 추가 및 스와이프 삭제할 수 있는 심플한 안드로이드 앱입니다. Java로 개발되었으며 한 화면에서 모든 기능을 수행할 수 있도록 설계되었습니다.

---

## ✅ 주요 기능

- 할 일 입력 및 리스트에 추가
- RecyclerView를 활용한 목록 표시
- 항목 스와이프 시 삭제 처리
- 빈 입력 예외 처리
- 간단하고 직관적인 UI

---

## 🧠 개발 동기 및 기획

- Android 개발 기초 학습을 위한 간단한 실습 프로젝트
- 사용자의 입력을 리스트 형태로 추가/삭제하는 기본 기능을 연습
- RecyclerView와 ItemTouchHelper를 실제로 적용

---

## 🔍 SWOT 분석

| 항목 | 설명 |
|------|------|
| **S (강점)** | - Java로 구현되어 Android 입문자에게 익숙함<br>- 스와이프 삭제 등 기본 기능 충실<br>- 간단하고 직관적인 UI |
| **W (약점)** | - 영구 저장 기능 없음<br>- 사용자 경험 개선 여지 존재 (애니메이션, 카테고리 등)<br>- 디자인 요소 미흡 |
| **O (기회)** | - Room DB 등을 추가하여 실용적인 앱으로 확장 가능<br>- MVVM, LiveData 등 구조 적용 연습 가능 |
| **T (위협)** | - 유사한 앱들이 매우 많아 차별화 어려움<br>- 자바보다 Kotlin으로의 전환 추세 |

---

## 🔧 사용 기술

- 언어: Java
- IDE: Android Studio
- 주요 구성요소: EditText, Button, RecyclerView, Adapter, ItemTouchHelper

---

## 🏗️ 프로젝트 구조

To_Do/
├── java/com/example/to_do/
│ ├── MainActivity.java
│ └── TaskAdapter.java
└── res/layout/
├── activity_main.xml
└── item_task.xml


---

## 📱 앱 화면 설명

- **상단**: 할 일 입력란 + '할 일 추가' 버튼  
- **하단**: RecyclerView를 통한 할 일 리스트 표시  
- **스와이프**: 좌우 스와이프 시 항목 삭제

---

## 🚀 향후 개선 아이디어

- 할 일 저장 기능 (Room, SharedPreferences)
- 완료 체크 기능
- 날짜/시간 설정 기능
- 다크모드 및 디자인 개선
- Firebase 연동하여 멀티디바이스 동기화

---
