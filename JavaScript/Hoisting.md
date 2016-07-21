# Hoisting
## Hoisting?
후 선언되 변수나, 함수들이 해당 Scope에서 최상위에 위치하는 것을 뜻한다.

자바스크립트엔진은 해당 Execution Context의 생성시, 즉 Runtime 시점에서
변수선언문이나 함수선언문을 읽기 전에 선언된 변수와 함수들을 다른 무엇보다도
먼저 읽어 Scope의 최상위에 위치시킨다.

이 덕에 뒤에서 선언된 함수들과 변수들을 그 전에 사용이 가능하다

* 변수의 경우에는 Variable Declaration(변수 선언)만 Hoisting 된다.
즉 Variable Intialization이 있다면 변수가 선언은 되나, 변수에 어떤 값도 들어가지 않는다.
* 함수의 Execution Context의 생성은 함수 호출 시 이뤄지므로, 
함수 내부에서 선언된 변수들은 함수 호출시에 Hoisting 된다.