# 3장 모든 객체의 공통 메서드
Object는 기본적으로 상속해서 사용하도록 설계됨

final이 아닌 메서드 (equals, hashCode, toString, clone, finalize)는 모두 재정의(overriding)을 염두에서 두고 설계된 것.

Object를 상속하는 클래스 즉, 모든 클래스는 이 메서드들을 일반 규약에 맞게 재정의 해야함.

메서드를 잘못 구현하면 대상 클래스가 이 규약을 준수한다고 가정하는 클래스(HashMap, HashSet 등)를 오동작하게 만듦.

final이 아닌 메서드들을 언제 어떻게 재정의 해야하는지 다룸. 
