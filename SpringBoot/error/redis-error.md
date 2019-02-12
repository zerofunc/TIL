# 레디스 설정 관련 에러
## 문제
`compile 'org.springframework.session:spring-session-data-redis'` 를 의존성에 추가했는데 아래와 같은 에러가 발생했다.


```
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.!
4> 16:59:45|main                |ERROR|o.s.b.d.LoggingFailureAnalysisReporter|report|42|==> |

***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of method redisTemplate in org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration required a bean of type 'org.springframework.data.redis.connection.RedisConnectionFactory' that could not be found.
	- Bean method 'redisConnectionFactory' not loaded because @ConditionalOnClass did not find required class 'redis.clients.jedis.Jedis'
	- Bean method 'redisConnectionFactory' not loaded because @ConditionalOnClass did not find required class 'io.lettuce.core.RedisClient'


Action:

Consider revisiting the entries above or defining a bean of type 'org.springframework.data.redis.connection.RedisConnectionFactory' in your configuration.
!
```

## 해결 방법
`compile 'io.lettuce:lettuce-core'` 의존성을 추가한다!