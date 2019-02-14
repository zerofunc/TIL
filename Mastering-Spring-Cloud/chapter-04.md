# 4장 서비스 디스커버리
- 유레카
    - 넷플릭스 OSS 디스커버리 서버
- 다룰 내용
    - 유레카 서버를 내장한 애플리케이션 배포하기
    - 클라이언트 측 애플리케이션에서 유레카 서버에 연결하기
    - 고급 디스커버리 클라이언트 설정
    - 클라이언트와 서버 사이의 보안 통신하기
    - 가용성을 높이기 위한 설정 및 동료 간 복제 메커니즘
    - 다른 가용 존에 클라이언트 측 애플리케이션의 인스턴스 등록하기
## 서버 측에서 유레카 서버 실행하기
- 단일 인스턴스로 실행할 경우 시작 시에 에러 로그만 찍힘
    - `spring-cloud-starter-netflix-eureka-server` 의존성을 제거
    - 컨피규레이션 속성의 디스커버리 클라이언트를 비활성화
        ```yaml
        server:
          port: ${PORT:8761}
        eureka:
          client:
            register-with-eureka: false
            fetch-registry: false
        ```
- `http:localhost:8761/eureka/apps` 서버 목록 조회
## 클라이언트 측에서 유레카 활성하기
- `'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'` 의존성 추가

- 유레카 클라이언트의 역할
    - 유레카 서버에 자신을 등록하고 호스트, 포트, 상태 정보 URL, 홈페이지 URL을 보냄  
    - 서버로부터 데이터를 가져와 캐싱하고 주기적으로 변경사항을 점검
        - 활성화 방법 2가지
            - `@EnableDiscoveryClient` 애노테이션을 메인 클래스에 추가해 활성화함
                - @EnableDiscoveryClient 는 spring-cloud-commons에 존재
            - 컨설, 유레카, 주키퍼 등 다수의 클라이언트 구현체가 클래스 경로상에 있을 경우  `@EnableEurekaCliet`로 활성화
                - @EnableEurekaCliet 는 spring-cloud-netfilx에 존재, 유레카만을 위해 작동함
            
- 유레카 서버는 서비스의 각 인스턴스로부터 생존신호(heartbeat)메시지를 받음
    - 설정된 기간 동안 하트비트 메시지를 못받으면 레지스트리에서 서비스가 삭제됨
```yaml
spring:
  application:
    name: client-service
server:
  port: ${PORT:8081}
eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_URL:http://localhost:8761/eureka/}
```

## 종료 시 등록 해제
- 중단된 이벤트를 가로채거나 이벤트를 서버에 보내기 위한 좋은 방법
    - 스프링 액추에이터의 `/shutdown` API 활용
    - `spring-boot-starter-actuator` 의존성 추가
    - 액추에이터는 기본으로 비활성화 돼있어 설정 속성을 통해 활성화 해야함. 
    - user/password 보안 해제
        ```yaml
        management:
          endpoint:
            shutdown:
              enabled: true
        ```
- 서버 머신이 재시작하거나 애플리케이션 장애, 서버와 클라이언트 간의 네트워크 인터페이스 문제 등 예상하지 못한 문제가 발생할 수 있음
    - 디스커버리 종료 절차가 시작되지 못해 서비스가 여전히 유레카 대시보드에 UP 상태로 나옴
    - 유레카에 등록된 상태는 절대 만료되지 않음
- Self-preservation mode(자기보호 모드)
    - 자신의 서비스 등록 상태를 제 시간에 갱신하지 않는 서비스가 일정 수를 넘으면 등록 만료를 멈춤
    - 네트워크 장애 발생 시 등록된 모든 서비스가 해제되는 것을 방지
    - 비활성화 방법
        ```yaml
        eureka:
            server:
                enable-self-preservation: false
        ```            
## 프로그램 방식으로 디스커버리 클라이언트 사용하기
- 프로그래밍 방식으로 유레카 클라이언트 API 사용하는 2가지 방법
    - `com.netflix.discovery.EurekaClient` : 유레카 서버가 노출하는 모든 HTTP API를 구현한다. 유레카 API 영역에 설명돼 있다.
    - `org.springframework.cloud.client.discovery.DiscoveryClient` : 넷플릭스 EurekaCluent를 대체하는 스프링 클라우드의 구현이다. 이것은 모든 디스커버리 클라이언트용으로 사용하는 간단한 범용 API다. 여기에는 getServices와 getInstances의 두가지 메소드가 있음
        ```java
        @RestController
        public class DiscoveryController {
            private Logger LOGGER = LoggerFactory.getLogger(DiscoveryController.class);
        
            @Autowired
            private DiscoveryClient discoveryClient;
        
            @GetMapping("/ping")
            public List<ServiceInstance> ping() {
                List<ServiceInstance> instances = discoveryClient.getInstances("CLIENT-SERVICE");
                LOGGER.info("INSTANCES: count={}", instances.size());
                instances.stream()
                        .forEach(it -> LOGGER.info("INSTATNCE: id={}, port={}", it.getServiceId(), it.getPort()));
        
                return instances;
            }
        }
        ```
    - 서비스 시작 후 `/ping` 종단점 호출 시 어떤 인스턴스도 표시하지 않음. 응답 캐싱 메커니즘과 관련있음

## 고급 컨피규레이션 설정
#### 유레카의 컨피규레이션 설정 세 가지
- 서버
    - 서버의 행동을 재정의함
    - eureka.server.*을 접두어로 사용하는 모든 속성을 포함
    - 전체 속성 목록은 EurekaServerConfigBean 클래스를 참조 (https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-server/src/main/java/org/springframework/cloud/netflix/eureka/server/EurekaServerConfigBean.java)
- 클라이언트
    - 유레카 클라이언트에서 사용할 수 있는 두 가지 속성 중 하나
    - 클라이언트가 레지스트리에서 다른 서비스의 정보를 얻기 위해 질의하는 방법의 컨피규레이션을 담당
    - eureka.client.* 를 접두어로 사용하는 모든 속성을 포함
    - 전체 속성 목록은 EurekaClientConfigBean 클래스를 참조 (https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/EurekaClientConfigBean.java)
- 인스턴스  
    - 포트나 이름 등의 현재 유레카 클라이언트의 행동을 재정의함
    - eureka.instance.*를 접두어로 사용하는 모든 속성을 포함
    - 전체 속성 목록은 EurekaInstanceConfigBean 클래스를 참조 (https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/EurekaInstanceConfigBean.java)
    
## 레지스트리 갱신하기
- self-preservation mode를 비활성화 해도 서버가 임대를 취소하는 것은 오래걸림
    - 모든 클라이언트 서비스가 30초(기본값)마다 서버로 하트비트를 보냄
        - eureka.instance.lease-renewal-interval-in-seconds 속성에 구성. 클라이언트에 설정
        - 서버가 하트비트를 받지 못하면 레지스트리에서 인스턴스를 제거하기 전에 90초 대기
    - 등록을 해제해서 인스턴스로 더 이상 트래픽이 가지 못하게 차단
        - eureka.instance.lease-expiration-duration-in-seconds 속성으로 구성. 클라이언트에 설정
    
    - 클라이언트 설정
        ```yaml
        eureka:
          instance:
            lease-renewal-interval-in-seconds: 1
            lease-expiration-duration-in-seconds: 2
        ```
    - 서버 설정
        - 유레카는 퇴거(evict) 태스크를 백그라운드로 실행.
        - 클라이언트로부터 하트비트가 계속 수신되는지 점검
        - 기본 60초마다 실행
        - 최악의 경우 임대를 갱신하는 주기와 임대를 만료하는 기간이 상대적으로 작은 값으로 설정돼있으면 서비스 인스턴스를 제거하는 데 60초 걸림
        - 타이머 틱(tick)의 지연은 `eviction-interval-timer-in-ms` 속성으로 설정. 단위 : 밀리초
            ```yaml
            eureka:
              server:
                enable-self-preservation: false
                eviction-interval-timer-in-ms: 3000
            ```
## 인스턴스 식별자 변경하기
- 유레카에 등록된 인스턴스는 이름으로 묶임
- 서버가 인식할 수 있는 유일한 ID를 보내야함
- 서비스 그룹의 **status** 열에 instanceId가 표시됨
- 스프링 클라우드 유레카의 식별자 자동 생성 방식
   ```groovy
    ${spring.cloud.client.hostname}:${spring.application.name}:${spring.application.instance_id:${server.port}}.
    ```
- `eureka.instance.instance-id` 속성을 이용해 재정의 가능
    ```yaml
    server:
    port: 808${SEQUENCE_NO}
    eureka:
      client:
      instance:
        instance-id: ${spring.application.name}-${SEQUENCE_NO}
    ```    
## IP주소 우선하기
- 모든 인스턴스는 호스트명으로 등록됨
- 보통은 마이크로서비스의 경우 DNS가 없음
    - 해결을 위한 대안
        - 유레카의 등록 절차 컨피규레이션 설정에서 호스트명 대신 서비스의 IP주소를 사용
        - `prefer-ip-address` 속성을 true로 설정
- 레지스트리의 모든 서비스 인스턴스는 유레카 대시보드에 호스트명을 담은 instanceId를 사용
- 링크를 클릭하면 IP 주소 기반으로 리디렉션됨
    - 리본 클라이언트도 같은 원리를 따름
- 서비스의 네트워크 위치를 결정하기 위한 방법으로 IP 주소 사용 시 문제가 발생
    - 머신에 하나 이상의 네트워크 인터페이스가 있을 경우
    - 올바른 인터페이스 선택을 위해 컨피규레이션 파일에 무시할 패턴의 목록을 정의
    ```yaml
    spring:
      cloud:
        inetutils:
          preferred-networks: 
            - 192.168
          ignored-interfaces: 
            - eth1*
    ``` 
## 응답 캐시
- 유레카 서버는 기본적으로 응답을 캐싱
- 캐시는 30초마다 무효화됨
- `/eureka/apss` API로 확인 가능
- 응답 캐시의 타임아웃 재정의
    - `responseCacheUpdateIntervalsMs 속성`
- 대시보드에 등록된 인스턴스 표시하는 부분은 캐시가 없음
```yaml
eureka:
  server:
    response-cache-update-interval-ms: 3000
```
- 클라이언트 측에서도 유레카 레지스트리를 캐싱함
    - 서버에서 캐시 타임아웃을 변경해도 클라이언트에서 갱신되는 데 시간이 걸림
    - 레지스트리는 30초마다 실행되는 백그라운드 태스크에 의해 비동기로 갱신
        - `registry-fetch-interval-seconds` 속성
    - 마지막으로 시도한 값에서 변경된 내용만 가져옴
        - `disable-delta` 속성으로 비활성화 가능    
- 유레카를 이용해 단위 테스트 개발 시 캐시 없이 즉시 응답을 받아야함
```yaml
eureka:
  client:
    registry-fetch-interval-seconds: 3
    disable-delta: true
```
## 클라이언트와 서버 간의 보안 통신 사용하기
- 프로젝트 의존성에 시큐리티 스타터 추가
	```groovy
	compile 'org.springframework.boot:spring-boot-starter-security'
	```
- application.yml 컨피규레이션 설정을 변경해 보안을 활성화하고 기본 자격 증명을 설정함
	```yaml
	spring:  
	 security: 
	   user: 
	    name: admin  
	    password: admin123
	```
- HTTP 기본 인증을 활성화하는 클라이언트 측 컨피규레이션
	```yaml
	eureka:  
	 client: 
	   service-url: 
	     defaultZone: http://admin:admin123@localhost:8761/eureka/
	```
- 디스커버리 클라이언트와 서버 간에 더 진보된 보안을 위해선 `DiscoveryClientOptionalArgs`를 맞춤형으로 구현해야함
-  12장 **API 보안 강화하기**에서 한 가지 예를 다룰 것임. 특히 스프링 클라우드 애플리케이션의 보안에 종속적인 사례를 논의할 것임.

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE5NzkyMjEyODEsMTgwNDgzNTk4OSw2MT
U3NjQ0MDEsMTQ2MjM3ODIxMSwxNzIzMDcxMDMwLC0zODI2NTYz
MjZdfQ==
-->