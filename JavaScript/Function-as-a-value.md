# 값으로서의 함수

Javascript에서는 함수도 객체 입니다.
함수가 값이 될 수 있습니다.

## 메소드
함수는 값이라서 객체안에 저장될 수 있습니다.

``` javascript
a = {
    b : function() {
    }
}
```

객체의 속성 값으로 담긴 함수를 메소드라고 부릅니다.

## 함수의 매개변수

또한, 함수는 값이기 때문에 다른함수의 인자로 전달 될수 있습니다. 아래 예제를 보면 알 수 있습니다.

``` javascript
function execute(func, name) {
    return func(name)
}

function hello(name) {
    return 'hello '+ name + '!';
}

function bye(name) {
    return 'bye '+ name + '!';
}

alert(execute(hello, 'Mr.Yoo'));
alert(execute(bye, 'Mr.Yoo'));
```

## 리턴 값
함수는 함수의 리턴 값으로도 사용할 수 있습니다.

``` javascript
function cal(mode) {
    var funcs = {
        'add' : function(first, second){
                    return first + second;
                }
        'sub' : function(first, second){
                    return frist - second;
                }
    }
    return funcs[mode];
}
alert(cal('add')(10,20));
alert(cal('sub')(5,2));
```

alert(cal('add')(10,20)) 를 실행하면 10과 20의 합인 30이 나옵니다.
cal('add')가 실행되면 
function(first, second){return first + second;} 가 리턴됩니다.
cal('add') 괄호가 나오면 함수를 호출시키며 괄호안에 있는 값을 인자로 전달합니다.
10과 20을 인자로 전달한 후 리턴되는 값이 경고창에 출력됩니다.

## 배열의 값
``` javascript
var funcs = [
    function(){return 'hello';},
    function(){return 'javascript';},
    function(){return '!';}
]

var i = 0;

for(i; i < funcs.length; ++i){
    alert(funcs[i]);
}
```

순서대로 "hello", "javascript", "!" 란 문자열이 경고창에 출력됩니다.


