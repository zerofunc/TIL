# public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
## 모든 필드가 public일 경우
- 캡슐화의 이점을 제공하지 못함
- API수정 없이 내부 표현 변경 x
- 불변식 보장 x
- 외부에서 필드 접근 시 부수 작업 수행 x

## getter 메소드 제공
- 클래스 내부 표현 방식을 언제든 바꿀 수 있는 유연성 획득
- package-private 혹은 private 중첩 클래스
    - 필드를 노출해도 OK
    - 코드가 깔끔
- 자바 플랫폼 라이브러리에서도 public 클래스의 필드를 직접 노출말라는 규칙 어김
    - ex) point, Dimension
- 불변일경우 노출할 때 단점이 줄어들지만 좋은 생각 아님
    - API수정 없이 내부 표현 변경 X
    - 필드를 읽을 때 부수 작업 수행 X