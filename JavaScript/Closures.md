# Closures

## Closures?
정의를 알기 전 먼저 알아야할 것이 있습니다.
지역변수는 함수가 실행될 때 생성되고 함수가 종료될 때 사라집니다.
하지만 클로저를 이용하면 이 규칙을 무시할 수 있습니다.

아래 예제를 살펴봅시다.
``` javascript
function hello(name) {
    var output = "Hello " + name;
    return function() {
        console.log(output);
    }
}
var myFunc = hello('JS'); 
myFunc();
```
결과는 아래와 같습니다
```
hello JS
```
변수 out은 지역변수 이므로 함수가 종료될 때 사라져야 합니다.
하지만 사라지지 않고 "hello JS"라는 값이 출력됐습니다.
이 것이 바로 클로저의 특징입니다.
 

내부함수는 외부함수의 지역변수에 접근할 수 있는데 외부함수의 실행이 끝난 
이후에도 내부함수가 외부함수의 변수에 접근 할 수 있다.
이런 매커니즘을 클로저(closure)라고 한다.

클로저의 스코프체인에는 자기 자신과 외부함수, 전역컨테스트의 변수 객체가 담깁니다.
일반적으로 함수가 실행을 마치면 함수의 스코프 및 그에 담긴 변수 전체가 파괴됩니다.
함수가 클로저를 반환했다면 해당 함수의 스코프는 클로저가 존재하는 동안에는 메모리에 계속 존재합니다.



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

/
컨택스트는 해당 컨택스트가 끝이 나면 destroy 상태가 됩니다. 즉 제거가 됩니다. 담고있던 정보와 함께 말이지요..
그런데 아래와 같이 컨택스트정보가 스택에 저장에 되어있다고 가정해 보겠습니다.

[       EC2      ]
[       EC1      ]      [       EC1      ]
[ Global Context]      [ Global Context]

위와 같은 구조, 즉 EC2가 EC1의 내부 함수 이고 순서상 EC2가 먼저 실행이 완료되어 삭제가 됩니다. 그리고 EC1차례가 되었는데 내부 함수인 EC2를 리턴해야 한다면 이게 논리적으로 존재하지 않는 대상을 리턴해야되는 어처구니 없는 일이벌어집니다. 더구나 이 이후에 다른 컨택스트가 EC2를 활성화 시켜야 하는 경우도 있게됩니다. 존재하지 않는 컨택스트에 접근을 하고 활성화를 시키는 것이 말이 안되는 거죠..
결국 이미 없어진 객체를 계속적으로 사용하게 되는 오류가 발생하게 됩니다.

그래서 등장하게 되는 개념이 클로저(closure) 입니다.

## Private Member 
클로저를 이용해 비공개 멤버를 만들 수 있습니다.
예제는 아래와 같습니다.
``` javascript
function Person() {
    // 비공개 멤버
    var name;
    this.getName = function () {
        return name;
    };
    this.setName = function(name) {
        this.name = name;
    }
}

var person = new Person();
person.setName("MIT");
console.log(person.getName());
```
출력은 아래와 같습니다.
```
MIT
```

비공개 맴버에 접근권한을 가진 공개매서드를 특권 메소드라고 합니다.
위 예제에서 Persone객체의 함수 getName은 비공개 멤버 name에 접근할 수 있으니 특권 메소드라고 할 수 있습니다.



*주의할 점 
 클로저는 스코프를 더 관리해야 하므로 과용하면 메모리 소비가 증가할 수 있습니다.
 메모리 누수
 클로저는 인터넷 익스플로러 9 이전 버전에서 메모리 문제를 일으킴
 JScript 객체와 COM 객체에 사용하는 가비지 컬렉션 방법이 다르기 때
 익명 함수는 assignHandler() 함수의 활성화 객체에 대한 참조를 계속 유지하는데 이 때문에 element에 대한 참조 카운트가 최소 1에서 더이상 줄어들지 않아서 메모리를 회수할 수 없음
 새로 고칠 함수에서는 element의 ID 사본을 클로저에서 사용하는 변수에 저장하여 순환 참조를 막음
 하지만 클로저는 외부 함수의 전체 활성화 객체에 대한 참조를 저장하므로 element도 여기에 포함
 따라서 필요하다면 element 변수에 null을 할당함
 그러면 COM 객체에 대한 참조가 제거되고 참조 카운트도 감소하므로 메모리를 회수할 수 있음