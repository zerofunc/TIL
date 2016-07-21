# Execution Context


## Execution Context
흔히 말하는 Context이다. 

Execution Context(실행 영역)는 스크립트가 실행되면서 생성된다(Global Context)
각각의 Active Context들은 생성된 순서대로 Stack 메모리에 삽입되게 되고,
Stack의 FILO 원리대로 현재 실행중인 Context는 Stack의 최상위에 위치하게 된다.

 
각각의 Context는 언제나
1개의 Variable Environment(변수 환경)

1개의 Lexical Environment
 
1개의 ThisBinding 속성 

총 3개의 부분으로 구성돼 있다.
그리고 3속성 모두 Object 형식으로 저장된다( 자바스크립트 Object 자료형으로 저장되는게 아니므로 사용자가 Access할 방법은 없다)


## Lexical Environment Object
해당 Context에서 선언된 변수,함수들의 Reference 값을 저장하는 객체이다.
(내부 구성은 Variable Environment Object 에 저장된다)

Idetifiers(변수/함수 이름)을 Reference로 변환할 때 사용된다.

Execution Context의 생성 초기시점에는 Variable Environment Object와 정확히 같은 값을 가지나,
Context 내부에서 Scope Augmentation이 실행될 시, 
Variable Environment Object와는 달리 새로운 Identifier와 그의 Reference값이 추가된다.

## Variable Environment object
Lexical Environment에 포함되는 개념이나, 위에서 설명한 Lexical Environment Object가
생성 후에 내부 값이 변할 수 있는 것과 달리
Variable Environment Object는 내부에서 선언된

* 변수(Variables)
* 함수 선언(Function Declarations)
* 함수 매개 변수(Fomal Parameter)

들을 저장하기 때문에 Hoisting등 this 키워드를 이용한
Expresssion에 의해 새로운 변수/함수가 등장하더라도
절대로 값이 변하지 않는다.

## ThisBinding Object
ThisBinding 객체는 해당 Execution Context의 this 키워드의 반환 값을 저장한다.
Execution Context에서 사용자가 유일하게 접근 가능한 부분이다.

참고로 this 키워드는 현재 Context가 참조하고 있는 객체를 가리키며
이 값은 어떻게 함수가 호출되었냐에 따라 갈린다.

Execution Context내부의 모든 코드가 실행이 완료된 후
Execution Context는 Stack에서 삭제되며,
그와 관련된 Lexical Environment Object, Variable Environmnet Object,
This Binding Object 모든 구성 환경 값들 또한 삭제 된다.

Global Context는 최상위이자 가장 외부의 Execution Context로
function 외부에서 선언된 모든 함수들과 변수들이 이에 포함된다.

웹 브라우저 환경에서 Global Context의 Variable Environment Object는
Window Object이며, Global Context에서 선언된 함수들과 변수들은 모두
Window Object에 저장된다.

이 Execution Context 내부의 모든 코드가 실행이 되고 난 후에는,
Window Object에서 삭제되고, 그안에 저장된 함수들과 변수들 또한 사라진다.
Window Object으ㅟ의 Global Context는 웹페이지가 종료되고 난 후에 사라진다.


