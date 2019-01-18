# 아이템 22. 인터페이스는 타입을 정의하는 용도로만 사용하라
- 상수 인터페이스 안티 패턴
    - stati final 필드만 있는 인터페이스
        - 클래스 내부에서 사용하는 상수는 내부 구현에 해당함
        - 상수가 API로 노출돼 클라이언트 코드가 이 상수들에 종속됨
        - ex) java.io.ObjectStreamConstants
- 상수를 공개하는 합당한 방법
    - 특정 클래스나 인터페이스와 강하게 연관된 상수는 클래스나 인터페이스 자체에 추가
    - 열거 타입
    - 유틸리티 클래스

```
자바 7부터 숫자 리터럴에 밑줄(_) 사용가능
숫자 리터럴 값에 영향을 주지 않으면서 가독성이 좋아짐
소수점 5자리 이상이라면 밑줄을 사용하는 걸 고려
십진수 리터럴도 세 자리씩 묶어주면 좋음
```

## 핵심 정리
- 인터페이스는 타입을 정의하는 용도로만 사용해야 함
- 상수 공개용 수단으로 사용 X