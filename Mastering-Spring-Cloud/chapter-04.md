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
              sensitive: false
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
- 프로그래밍 방시긍로 유레카 클라이언트 API 사용하는 2가지 방법
    - `com.netflix.discovery.EurekaClient` : 유레카 서버가 노출하는 모든 HTTP API를 구현한다. 유레카 API 영역에 설명돼 있다.
    - `org.springframework.cloud.client.discovery.DiscoveryClient` : 넷플릭스 EurekaCluent를 대체하는 스프링 클라우드의 구현이다. 이것은 모든 디스커버리 클라이언트용으로 사용하는 간단한 범용 API다. 여기에는 getServices와 getInstances의 두가지 메소드가 있음
    - 서비스 시작 후 `/ping` 종단점 호출 시 어떤 인스턴스도 표시하지 않음. 응답 캐싱 메커니즘과 관련있음  