# Spring MVC 확장하는 방법
@Configuration , @WebMvcConfigurer

Spring MVC에서 제공해주는 설정외에 추가로 설정하고 싶은 부분을 확장해서 사용가능함.

# Spring MVC 재정의하는 방법
@Configuration, @EnableWebMvc

Spring MVC에서 제공해주는 설정을 무시하고 재정의할 수 있음.
spring.mvc.static-path-pattern
# HttpMessageConverters
HTTP 요청 본문을 객체로 변경하거나
객체를 HTTP ResponseBody로 변경할 때 사용
- @RequestBody
- @ResponseBody

# RestController
RestController 어노테이션을 컨트롤러에 사용하면 
Controller Method에 @ResponseBody를 생략해도 됨.

# HttpMessageConvertersAutoConfiguration
HttpMessageConverters에 대한 자동 설정을 해줌.

JacksonHttpMessageConvertersConfiguration 에서 Json을 xml로 convert 해주는 설정이 있음. 

XmlMapper가 클래스 패스에 있어야만 해당 설정이 동작하기에 XML 메시지 컨버터를 추가해줘야함.

#spring.mvc.static-path-pattern
기본 정적 리소스 기본 맵핑 경로를 지정하고 싶을 때 사용
```
spring.mvc.static-path-pattern=/static/**
```
위와 같이 설정 시 정적리소스에 접근할 때 앞에 /static/ 을 붙여줘야함.