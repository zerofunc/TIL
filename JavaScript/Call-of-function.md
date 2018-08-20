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
 
 


