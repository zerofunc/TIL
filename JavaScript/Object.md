# Object (객체)

배열에 데이터가 추가되면
배열 전체에서 중복되지 않는 인덱스가 자동으로 생성되어
추가된 데이터에 대한 식별자가 됩니다.
이 인덱스를 이용해 데이터를 가져올 수 있습니다.
인덱스로 문자를 사용하고 싶다면 객체를 사용해야 합니다.
다른 언어에서는 Map 또는 Dictionary, Associative Array라는 데이터 객체에 해당한다.

## How to create object

다음은 객체를 생성하는 방법입니다.

### 객체 생성 방법1
``` javascript
var fruit = { 
    'apple' : 1,
    'banana' : 3,
    'grape' : 10
}
```

apple은 key이고, 1은 value가 됩니다.

### 객체 생성 방법2
``` javascript
var fruit = {};
fruit['apple'] = 1;
fruit['banana'] = 3;
fruit['grape'] = 10;
```

### 객체 생성방법1
``` javascript
var fruit = new Object();
fruit['apple'] = 1;
fruit['banana'] = 3;
fruit['grape'] = 10;
```

## How to access attribute of object

### 객체의 속성에 접근하는 방법 1
``` javascript
var fruit = { 
    'apple' : 1,
    'banana' : 3,
    'grape' : 10
}
alert(fruit['apple']);
```

### 객체의 속성에 접근하는 방법 2
``` javascript
var fruit = { 
    'apple' : 1,
    'banana' : 3,
    'grape' : 10
}
alert(fruit.apple);
```

## Object And Repetition

``` javascript
var fruit = { 
    'apple' : 1,
    'banana' : 3,
    'grape' : 10
}

for(key in fruit) {
    alert("key : " + key + ", value : "+ fruit[key]");
}
```

결과는 아래와 같습니다.

``` 
key : apple, value : 1
 key : banana, value : 3
 key : grape, value : 10
```

for 문은 in 뒤에 적은 배열의 key값을 in 앞의 변수에 담아서
반복문을 실행합니다.
반복문이 실행 될 때 변수의 값에 apple, banana, grape 가 차례대로 할당됩니다.
그래서 fruit[key]를 통해 키에 해당하는 값을 알아낼 수 있습니다.

객체에는 객체를 담을 수도 있고, 함수를 담을 수도 있습니다.

``` javascript
var fruit = { 
    'list' : {'apple' : 1,'banana' : 3,'grape' : 10 },
    'print' : function() {
        for(var key in this.list) {
            console.log(key+':'this.list[key]);
        }
    }
}
 
```