# equals를 재정의하려거든 hashCode도 재정의하라
## equals를 재정의한 클래스에서 hashCode도 재정의 해야함
- HashMap, HashSet 의 원소로 사용할 때 문제가 발생함
```
- euqals 비교에 사용된 정보가 변경되지 않았을 시 애플리케이션이 실행되는 동안에는 그 객체의 hashCode는 항상 값을 반환
- if(A.equals(B)) A.hashCode == B.hashCode
- if(!A.equals(B)) A.hashCode != B.hashCode 일 필요는 없다. 다르면 해시테이블의 성능이 좋아짐
```
- HashMap은 해시코득다 다른 경우 동치성 비교를 하지 않음

## 좋은 해쉬 함수
- 서로 다른 인스턴스에 다른 해시코드를 반환
- result = Type.hashCode(f)
- result = 31  * result + c
- Arrays.hashCode

## Objects.hash
- 단 한줄로 해쉬 함수 작성 가능
- 성능이 느림
    - 박싱/언박싱
    - 배열이 만들어짐
    
    
## 주의 사항
- 성능을 높인다고 핵심필드를 생략 X 
    - 해시테이블 성능 저하
- hashCode 반환 규칙을 API 사용자에게 자세하게 공표 x
    - 클라이언트가 이 값의 의지하지않음.
    - 추후에 계산 방식 변경 가능
    