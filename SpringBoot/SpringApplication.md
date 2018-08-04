# SpringApplication

## Listener

스프링은 ApplicationEvent를 등록할 수 있음.

주의할 점은 ApplicationContext를 만들기 전에 사용하는 리스너는  ** Bean으로 등록되지 않음! **

## WebApplicationType

WebApplicationType에는 NONE, SERVLET, REACTIVE가 있다.
```
SpringApplication app = new SpringApplication(SpringinitApplication.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
```

setWebApplicationType이란 함수를 이용해 WebApplicationType을 지정할 수 있다.

WebApplicationType을 설정하지 않을 경우 

Spring MVC가 들어있으면 SERVLET으로

Spring Web-flux가 들어있으면 REACTIVE로 설정됩니다.

둘다 없으면 NONE으로 설정됩니다.
 