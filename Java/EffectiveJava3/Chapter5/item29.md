# 아이템29. 이왕이면 제네릭 타입으로 만들라
- 힙오염
```java
ArrayList<Integer> integers = new ArrayList<>();
ArrayList<String> strings = new ArrayList<>();
strings.add((String)(Object)integers.get(0)); // 컴파일러 에러가 나지 않음
```