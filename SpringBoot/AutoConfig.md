#스프링 부트 자동 설정
SpringBootApplication의 빈 등록은 두 단계로 나눠서 등록됨.

## 첫 번째 단계 
@ComponentScan으로 빈을 스캔해서 등록함.

## 두 번째 단계
spring-boot-autoconfigure META-INF 속 spring.factories 파일 
org.springframework.boot.autoconfigure.EnableAutoConfiguration에 정의된 클래스 목록들을 참조해서 자동 설정을 시작함.

위 내용과 관련된 어노테이션들은 간략하게 아래 목록을 참조. 

- ConditionalOnMissingBean
- ConditionalOnBean
- ConditionalOnProperty
- EnableConfigurationProperties
- ConditionalOnWebApplication 
등 ... 생략 
