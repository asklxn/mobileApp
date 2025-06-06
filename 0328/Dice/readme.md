# Dice Roller App

**Dice Roller**는 실제 주사위를 굴리는 것처럼 6면체 주사위 이미지를 빠르게 전환하며 랜덤 결과를 보여주는 Android 앱입니다.

## 주요 기능

- 주사위 이미지 6종을 사용해 실제 주사위 굴림 효과를 시각적으로 구현
- **롤 버튼 클릭 시** 100ms 간격으로 20번 이미지가 빠르게 변경되어 주사위가 굴러가는 듯한 애니메이션 제공
- 주사위가 굴러가는 동안 버튼 비활성화로 중복 입력 방지
- 주사위 굴림 종료 후 버튼 재활성화

## 사용 방법

1. 앱 실행 후 화면 중앙에 위치한 주사위 이미지를 확인합니다.
2. "롤" 버튼을 누르면 주사위가 빠르게 굴러가는 애니메이션이 시작됩니다.
3. 애니메이션이 끝나면 최종 주사위 눈이 결과로 표시됩니다.
4. 버튼이 다시 활성화되면 원하는 만큼 반복해서 주사위를 굴릴 수 있습니다.

## 구현 세부 사항

- `Handler`와 `Runnable`을 이용해 주사위 눈 이미지를 100밀리초 간격으로 20번 변경
- `Random` 클래스로 0~5 사이 난수를 생성해 주사위 눈 결정
- UI 요소: `ImageView`로 주사위 눈 표시, `Button`으로 주사위 굴림 시작

