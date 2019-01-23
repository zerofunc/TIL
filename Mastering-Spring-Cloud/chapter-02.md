# 마이크로서비스를 위한 스프링
## 스프링 부트 소개
- 스타터? 
    - 프로젝트 의존성에 포함될 수 있는 아티팩트(artifact)
    - 기대하는 기능을 구현하기 위해 애플리케이션에 포함해야 하는 다른 의존성을 제공
    - 즉시 사용 가능. 별도의 설정 필요 X
    - 명명 패턴 `spring-boot-starter-*` *은 스타터의 특정 타입

| 이름                             | 설명                                                                                                     |
|----------------------------------|----------------------------------------------------------------------------------------------------------|
| spring-boot-starter              | 자동 컨피규레이션 지원, 로깅, YAML을 포함하는 핵심 스타터                                                |
| spring-boot-start-web            | RESTful과 스프링 MVC를 포함하는 웹 애플리케이션 개발. 톰캣(Tomcat)을 기본 컨테이너로 내장                |
| spring-boot-starter-jetty        | 기본 내장 서블릿 컨테이너로 제티(Jetty)를 프로젝트에 포함                                                |
| spring-boot-starter-undertow     | 기본 내장 서블릿 컨테이너로 언더토우(Undertow)를 프로젝트에 포함                                         |
| spring-boot-starter-tomcat       | 내장 서블릿 컨테이너로 톰캣을 프로젝트에 포함. spring-boot-starter-web에 사용되는 기본 서블릿 컨테이너   |
| spring-boot-starter-actuator     | 애플리케이션 모니터링 및 관리 기능을 제공하는 스프링 부트 액추에이터 프로젝트를 포함                     |
| spring-boot-starter-jdbc         | Tomcat Connection Pool을 포함하는 스프링 JDBC를 포함. 특정 떼이터베이스의 드라이버는 직접 제공해야 함    |
| spring-boot-starter-data-jpa     | JPA또는 Hibernate를 이용해 관계형 데이터베이스에 상호작용하기, 위해 필요한 모든 아티팩트를 포함          |
| spring-boot-starter-data-mongodb | 몽고디비와 상호작용하고 로컬 호스트의 몽고에 대한 클라이언트 연결을 초기화하기 위한 모든 아티팩트를 포함 |
| spring-boot-starter-security     | 프로젝트에 스프링 시큐리티를 포함하고 애플리케이션에 기본 시큐리티를 활성화                              |
| spring-boot-starter-test         | JUnit, 햄크레스트(Hamcrest), 모키토(Mockito)와 같은 라이브러리를 활용한 단위 테스트의 생성을 허가        |
| spring-boot-starter-amqp         | 스프링 AMQP를 프로젝트에 추가하고 기본 AMQP브로커로서 래빗엠큐를 시작                                    |

- 스프링 프레임워크의 표준 컨피규레이션과 스프링 부트의 주요 차이점
    - 표준 스프링 컨피규레이션
        - 애플리케이션을 WAR형태로 웹 컨테이너에 배포
    - 스프링 부트
        - 웹 컨테이너를 애플리케이션에 포함할 수 있음
        - executable jar
- 마이크로서비스에서 피해야 할 패턴
    - 하나의 웹 컨테이너에 여러 WAR 파일을 배포하는 것
        - 이유 : 마이크로서비스의 가장 대표적 기능 중 하나는 다른 마이크로서비스와의 독립성
   
## 스프링 부트를 이용해 애플리케이션 개발하기
- `spring-boot-starter-web` 의존성 추가하기
- 애플리케이션 클래스 생성
- @SpringBootApplication 애노테이션 추가
    - @SpringBootApplication == @Configuration + @EnableAutoConfiguration + @ComponentScan
    
## 컨피규레이션 파일 사용자 정의하기
- 스프링 부트는 편리하게 설정을 관리할 수 있는 메커니즘을 제공함
- 지원하는 컨피규레이션 파일 타입
    - .properties, .yml
    - ex) application.properties, application.yml, application-prod.properties, application-dev.yml
- 운영체제 환경변수와 커맨드라인 입력값을 사용해 컨피규레이션을 외부로 뽑아낼 수 있음
    - 애플리케이션의 현재 디렉터리의 /config 하위 디렉터리
    - 애플리케이션의 현재 디렉터리
    - 클래스 경로 /config 패키지 (예를 들어 JAR 내부)
    - 클래스 경로 root
- 자신만의 컨피규레이션 속성을 지정하면 @Value 또는 @ConfigurationProperties 애노테이션을 통해 애플리케이션에 주입 가능
- 스프링 부트 프로젝트에서 지원되는 미리 정의된 속성 목록
    - https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
## RESTful 웹서비스 생성하기
- JSON 메시지를 직렬/역직렬화하는 잭슨(Jackson) 라이브러리는 `spring-boot-start-web`과 함께 자동으로 클래스 경로에 포함됨
- @RequestMapping
    - HTTP 컨트롤러 메서드와 HTTP를 대응하는 데 주로 사용
- @GetMapping == @RequestMapping(method=RequestMethod.GET)
- @PostMapping == @RequestMapping(method=RequestMethod.POST)
- @RequestBody
    - 입력 JSON을 잭슨 라이브러리를 사용해 Object로 바인딩
- REST는 WSDL과 같은 표준 표기법이 없다
- 초기에는 Web Application Description Language(WADL)이 그 역할을 수행함.

## API 문서화
- 스웨거는 RESTful API를 설계하고 빌드하고 문서화하는데 가장 많이 사용되는 도구
- made by 스마트 베어사
- 스웨거 사용 방식 2가지
    - 스웨거를 통해 표기법을 활용하 API를 설계하고 코드 생성
    - 소스코드로 시작해 스웨거 파일을 생성
    
## 스웨거 2를 스프링 부트와 같이 사용하기
- `io.springfox.springfox-swagger2`, `io.springfox.springfox-swagger-ui` 의존성 추가
- 메인 애플리케이션에 @EnableSwagger2 애노테이션 추가
- 애플리케이션 시작 시 스웨거 라이브러리에 의해 API문서가 소스코드로부터 자동 생성됨

## 스프링 부트 액추에이터 기능
- 애플리케이션을 모니터링하고 상호작용하기 위한 내장된 수많은 종단점 제공 
- `spring-boot-starter-actuator` 의존성 추가
- 중요한 액추에이터의 종단점 목록

| 경로     | 설명                                                                                                |
|----------|-----------------------------------------------------------------------------------------------------|
| /beans   | 애플리케이션에 초기화된 모든 스프링 빈의 목록을 표시                                                |
| /env     | 스프링의 설정 가능한 환경 속성 목록을 표시, 예를 들어 OS 환경 변수 및 컨피규레이션 파일의 속성 목록 |
| /health  | 애플리케이션의 상태 정보 표시                                                                       |
| /info    | 애플리케이션의 임의 정보 표시, 예를 들어 build-info.properties나 git.properties 파일의 정보 표시    |
| /loggers | 애플리케이션의 로거 컨피규레이션 정보를 표시하고 수정                                               |
| /metrics | 애플리케이션의 메트릭 정보를 표시(예: 메모리 사용량, 실행 중인 스레드 수, REST 메서드의 응답 시간)  |
| /trace   | 트레이스(trace) 정보 표시 (기본으로 마지막 100개의 HTTP요청)                                        |
  
- 프로젝트 보안 비활성화
```yaml
management:
    security:
      enabled: false
```

## 애플리케이션 정보
- `/info` API는 기본적으로 어떤 정보도 노출 X
- InfoContributor 빈을 변경하거나 자신만의 빈을 작성해야함
- EnvironmentInfoContributor
    - 환경 정보를 노출
- GitInfoContributor
    - 클래스 경로에 존재하는 git.properties 를 찾아 브랜치 이름이나 커밋 ID같은 커밋 정보 노출
- BuildInfoContributor
    - META-INF/build-info.properties 파일 정보를 모아서 노출
    - git-commit-id-plug 추가
    - spring-boot-maven-plugㅇ니수정
    
## 상태 정보
- `/health` API도 자동 설정 되는 것이 있음
- 디스크의 사용량, 메일 서비스, JMS, 데이터 소스, 몽고DB, 카산드라와 같은 NoSQL 모니터링 가능
- MongoHealthIndicator  
    - 몽고디비 상태 노출

## 매트릭스
- `/metrics` API로 서비스의 힙과 힙이 아닌 메모리의 사용량을 알 수 있음
- 로딩된 클래스의 개수, 활성화된 스레드의 수, API메서드의 평균 실행 시간 등 수많은 정보를 추가로 보여줌
- 자신만의 메트릭 만들기
    - CounterService
        - 값의 증가와 감소, 초기화를 위한 메서드 제공
    - GaugeService         
        - 현재의 값을 전달하는 메서드 제공
- 모든 메트릭은 분석하거나 표시할 수 있는 저장소로 전달 가능
    - 레디스(Redis), Open TSDB, statsd, 인플럭스디비(InfluxDB)등에 저장 가능

## 개발자 도구
- 프로젝트 클래스 경로 상의 파일이 변경되면 애플리케이션이 변경을 감지하여 자동으로 재시작 하는 기능
- `spring-boot-devtools` 의존성 추가
- 변경 시 애플리케이션이 재시작하지 않도록 리소스 제외 가능
```yaml
spring:
    devtools:
      restart:
        exclude: static/**
```
