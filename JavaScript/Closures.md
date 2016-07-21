# Closures

## Closures?
내부함수는 외부함수의 지역변수에 접근할 수 있는데 외부함수의 실행이 끝난 
이후에도 내부함수가 외부함수의 변수에 접근 할 수 있다.
이런 매커니즘을 클로저(closure)라고 한다.



내부함수는 호출된 외부함수에서 선언되었기 때문에 외부함수의 Execution Context의
Variable Environment 객체를 참고하고 있다.

자바스크립트의 Garbage Collector는 객체가 다른 인스턴스에 의해 참조되고 있는 동안은
해당 객체를 사용 중으로 판단하여 객체에 할당된 메모리를 수집하지 않는다.

외부함수 내에서 내부함수 객체를 반환 시, Function Expression 방식으로 참조된다.
이 때, 반환된 내부함수의 Variable Environment 객체는 사용중으로 마크되며,
내부함수의 Execution Context가 외부함수의 Variable Environment 객체를 참조하게 되니,
마찬가지로 외부함수의 Variable Environment 객체도 사용 중으로 마크된다.

외부함수의 실행이 끝나 정해진 값을 반환했음에도 불구하고 외부함수가 호출될 때 생성된
Variable Environment에 계속 접근이 가능한다.



