# 함수의 호출

## 함수를 호출하는 방법

가장 기본적인 방법은 아래와 같습니다.
``` javascript
function sub(num1, num2) {
    return num1 - num2;
}
sub(10,5)
```

``` javascript
function sub(num1, num2) {
    return num1 - num2;
}
sub.apply(null, [4,2]);
```

함수 sub는 Function객체의 인스턴스입니다.
그러므로 Function 객체의 메소드인 apply를 호출 할 수 있습니다.

위의 코드를 보시면 sub.apply의 2번 째 인자는 배열입니다.
이 배열의 담겨있는 원소가 함수(sub)의 매개변수로 순서대로 대입됩니다.
함수 sub의 매개변수인 num1에는 4가, num2에는 2가 대입됩니다.
 
``` javascript
var this=o1;
```

apply에 객체를 매개변수로 넘기면 함수가 실행되는 순간
매개변수로 남긴 객체가 함수의 this가 됩니다.
마치 이 함수가 매개변수로 넘긴 객체의 속성인 것 처럼 사용할 수 있습니다.
함수에서 this의 값이 전역객체가 아니라 매개변수가 된다는 의미입니다.
일반적인 객체지향 언어에서는 하나의 객체에 소속된 함수는 그 객체의 소유물이 됩니다.
하지만 javascript에서 함수는 독립적인 객체로 존재합니다.
apply나 call 메소드를 통해서 다른객체의 소유물인 것처럼 실행할 수 있습니다.

apply의 첫 번째 인자로 null을 전달하면 apply가 실행된 함수 인스턴스는
전역객체(window)를 맥락으로 실행한다.


