## MySQL Auto Increment Key 설정 관련 에러
Auto Increment키로 설정된 열은 행이 늘어날 때 마다 자동으로 1부터 1씩 증가합니다
## 문제
```sql
create table test
(
	seq int(11) unsigned auto_increment
);
```
MySQL에서 위 쿼리 처럼 AI(Auto Increment) Key를 설정할 때 해당 열을 `primary key` 지정하지 않으면 아래와 같은 에러가 발생합니다.

```
[42000][1075] Incorrect table definition; there can be only one auto column and it must be defined as a key
```

## 해결방법
아래 쿼리처럼 `primary key`로 지정해주면 문제가 해결됩니다.

```sql
create table mrs_pt.pt_tt1
(
	seq int(11) unsigned auto_increment primary key 
);
```
