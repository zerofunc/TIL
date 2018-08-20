# 로깅 퍼사드
## 로깅 퍼사드와 로거
로깅 퍼사드는 여러 로거 중 하나를 적용할 수 있게 해주는 퍼사드

로거를 자유롭게 쓰기 위해 주로 로깅 퍼사드를 이용함.

## Commons Logging 이슈
한 때 런타임 시 클래스 로깅 관련 이슈, 메모리릭 등 문제가 많아 개발자들이 기피함.
 
그래서 SLF4j라는 새로운 라이브러리가 나옴. 구조적으로 심플하고 안전한 퍼사드

## 스프링 부트가 Commons Logging 을 쓰는 이유
Spring Core가 만들어질 때 SLF4j가 없었다. 그래서 Commons Logging을 사용함.

## Spring-JCL
 Commons Logging을 컴파일 시에 SLF4j or Log4j2로 변경할 수 있게해줌
 
 Commons Logging이 SLF4j로 보내고 SLF4j가 Logback으로 보냄.
 
 ### 최종적으로 스프링 부트를 다른 설정없이 처음 시작할 땐 **Logback**이 로그를 찍음