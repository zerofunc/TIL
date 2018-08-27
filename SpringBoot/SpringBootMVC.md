# Spring MVC 확장하는 방법
@Configuration , @WebMvcConfigurer

Spring MVC에서 제공해주는 설정외에 추가로 설정하고 싶은 부분을 확장해서 사용가능함.

# Spring MVC 재정의하는 방법
@Configuration, @EnableWebMvc

Spring MVC에서 제공해주는 설정을 무시하고 재정의할 수 있음.

# HttpMessageConverters
HTTP 요청 본문을 객체로 변경하거나
객체를 HTTP ResponseBody로 변경할 때 사용
- @RequestBody
- @ResponseBody

# RestController
RestController 어노테이션을 컨트롤러에 사용하면 
Controller Method에 @ResponseBody를 생략해도 됨.
