# 아이템10. equlas는 일반 규약을 지켜 재정의하라
잘못 재정의하면 안좋을 결과를 초래함

## 재정의 하지 않아도 되는 상황

각인스턴스가 본질적으로 고유하다.

    - 값이 아니라 동작하는 개체를 표현하는 클래스. ex) Thread <= 질문
인스턴스의  '논리적 동치성(logical equality)'을 검사할 일이 없다.

    - 설계자가 논리적 동치성을 검사하는 방식을 원하지 않는 경우 기본 equals 사용
상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.

    - Set은 AbstractSet의 equals 사용
    - List는 AbstractList의 equals 사용
    - Map은 Abstract의 equals 사용
클래스가 private 이거나 package-private이고 equals 메서드를 호출할 일이 없다.
    
    - 필요하면 에외를 던지도록 구현
값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스 클래스
    - ex) enum, 싱글

## 재정의 해야하는 상황
논리적 동치성을 확인해야 하는데, 상위 클래스의 equal가 논리적 동치성 비교하도록 재정의 돼있지 않을 때
    
    - 주로 값 클래스 ex) dto, vo ..
    - 재정의 시 Map의 키와 Set의 원소로 사용
## 재정의 규약
- 반사성
    - null이 아닌 모든 참조 값 x에 대해 x.equals(x) == true
- 대칭성
    - null이 아닌 모든 참조 값 x,y에 대해 (x.eqauls(y) && y.equals(x)) == true 
- 추이성
    - null이 아닌 모든 참조 값 x,y,z에 대해 (x.equals(y) && y.equals(z) && x.equals(z)) == true
- 일관성
    - null이 아닌 모든 참조 값 x,y에 대해 x.equals(y) 의 값은 항상 동일
- null 아님
    - null이 아닌 모든 참조 값 x에 대해 x.equals(null) == false
    
## 특징
- 구체클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않음.

## 대칭성
- 자바 라이브러리에도 대칭성을 만족하지 못하는 클래스 존재.
    - 구체 클래스를 확장해 값을 추가
    - ex) TimeStamp - date

## 일관성
- equlas의 판단에 신뢰할 수 없는 자원이 끼어들게 해서는 안됨.
    - ex) java.net.URL
        - URL과 매핑된 호스트 IP주소를 비교함 
## null아님
- null 체크로 올바른 타입인지 검
## 리스코프 치환 원칙
- 어떤 타입에 있어 중요한 속성이라면 하위 타입에서도 중요하다.
- 상위 타윕은 하위타입을 대체할 수 있다.

## equals 메소드 구현방법
- ==  연산자를 사용해 입력이 자기 자신의 참조인지 확인
    - 자기자신이면 true 반환
- instanceof 연산자로 입력이 올바른 타입인지 확인
    - 올바르지 않으면 false 반환
    - ex) Set, List, Map, Entrt 등
        - 자신을 구현한 클래스끼리 비교
- 입력을 올바른 타입으로 형변환
- 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사
    - 모든 필드가 일칳파면 true, 아니면 false 반환
    
## 기본 타입필드 비교
- float과 double을 제외한 기본 타입필드는 == 연사자로 비교
    - float은 Float.compare, doubledms Double.compare
    - Float.NaN, -0.0f, 부동 소수값을 다룸
## null도 정상 값으로 취급하는 참조 타입 필드
- Objects.equals(Object, Object)로 NPE 예방

## 비교하기 복잡한 필드를 가진 경우
- 표준형을 저장해 표준형끼리 비교

## 필드 비교
- 다를 가능성이 더 크거나 비교하는 비용이 싼 필드를 먼저 비교
- 논리적 상태와 관련없는 필드 비교 x
    - ex) 동기화용 락
- 핵심필드로 계산 가능한 파생필드 비교 x
    - 파생필드로 비교하는게 빠른 경우도 있음. 캐싱할 부분만 비교할 때


## equals 를 구현하고 세 가지 자문
- 대칭적인가?
- 추이성이 있는가?
- 일관적인가?

## 주의사항
- equals를 재정의할 땐 hashCode도 반드시 재정의
- 너무 복잡하게 해결 X
- Object외의 타입을 매개변수로 받는 equals 메서드 선언 X
    - @Override 애너테이션 사용을 컴파일 단에서 검사

# Tip
- IDE에서 제공하는 기능을 사용
    - intelliJ의 Generate eqauls() and hashCode()