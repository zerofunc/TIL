#Amazon DynamoDB

- #### 높은 확장성
초당 수 백만개 요청 처리
수 백TB 용량 자동 확장

- #### 빠르고 일정한 성능
10ms 미만의 지연시간 보장
DAX로 micro초로 감소

- #### 완전 관리형 서비스
자동 프로비저닝 및 인프라 관리

- #### 높은 신뢰성
리전 내 여러 AZ로 데이터 복제, 세분화된 접근제어

- NoSQL 데이터 베이스
- 유여한 데이터 모델 - 문서 및 키-값 스토어
- 세분화된 접근 제어 (IAM)
- 이벤트 기반의 프로그래밍 환경 제공

#테이블 형태
키:값 혹은 JSON 방식

```
ItemList (item, ...)
{
    item = string : "sword" : 기본 키
    name = string : "칼" // 속성 이름 = 유형 : 속성 값
    attack = number : 5
}
{
    item = string : "shield"
    name = string : "방패"
    defense = number : 10 // 모든 속성을 다 가지거나, 동일할 필요 없음
}
```

#지원하는 데이터 형태
- ### 이름-값 저장소 모델
  - String(S)
  - Number (N)
  - Binary (B)
  - String Set (SS)
  - Number Set (NS)
  - Binary Set (BS)
- ### 문서 저장소 모델
  - Boolean (BOOL)
  - Null (NULL)
  - List (L)
  - Map (M)

# Index
- ### Global Secondary Index (GSI)
  - GSI가 선언되면 각각이 새로운 테이블과 같이 사용된다.
  별도의 테이블을 생성함
  - 각각의 GSI에 개별적으로 RCU/ WCU를 할당한다.
  - GSI에서는 이 인덱스의 목적에 맞는 다른 키를 활용할 수 있다
- ### Local Secondary Index (LSI)
  - Local Secondary Index는 10G의 크기 제한
  - LSI는 Table을 생성할 때 만들어진다.
  - 기존의 테이블에 LSI를 추가 할 수는 없다.
  - 기존 테이블의 Key구조를 그대로 사용한다.



