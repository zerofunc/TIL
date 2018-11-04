# Rest-API
[REST-API (http://meetup.toast.com/posts/92)](http://meetup.toast.com/posts/92)

- Representaitonal State Transfer의 약자
- 로이 필딩의 박사학문 논문에서 최초로 소개됨. HTTP 프로토콜 주요 저자

## REST 구성
- 자원(RESOURCE) - URI
- 행위(Verb) - HTTP METHOD
- 표현(Representations)

## REST 아키텍쳐의 6가지 제한 조건
- 클라이언트 / 서버 구조 : 클라이언트의 요청과 서버의 응답 기반
- 무상태 (Stateless) : 클라이언트의 상태와 관계없이 요청으로만 응답.
- 캐시 처리 가능 (Cacheable) : 클라이언트는 서버의 응답을 캐쉬. 네트워크 비용 절감
- 계층화 (Layered System) : 서버는 다양한 형태의 중간 계층을 이용해서 확장할 수 있다. 클라이언트는 서버의 계층에 무관하게 통신을 할 수 있다.
- Code on demand (optional) : 리소스를 다룰 수 있는 코드 전송 : 자바스크립트
- 인터페이스 일관성 : 시스템 구조를 단순화시키고 작은 단위로 분리해서 독립적으로 개선하고 확장할 수 있다.

REST 하게 만든다 -> URL 주소만 보고도 수행하려는 동작을 알 수 있게 만드는 것



## REST 인터페이스 원칙
- 자원 식별 : 개별 리소스를 식별할 수 있어야 한다.  예 : URL이나 URI
- 메세지로 리소스 조작 : 메세지에 작성한 리소스를 다루는 정보를 이용해서 리소스를 얻어오거나 리소스를 조작한다.
- 자기 서술적 메세지 : 요청과 응답 메세지에는 메세지를 처리할 수 있는 정보를 포함한다.
- 하이퍼미디어 : 하이퍼링크를 이용해서 유기적으로 연결된 문서로 쉽고 간단하게 정보를 접할 수 있다.

## 인터페이스 설계
- 간단하고 직관적인 API
- 리소스를 다루는 행위는 HTTP 메소드 사용 : GET, POST, PUT, DELETE

## URI 설계 시 주의할 점
- url의 의미중 명사를 리소스로 만드는 것을 권장함
- 슬래시 구분자(/)는 계층 관계를 나타내는 데 사용
```
http://mit.com/food/meat/pork
```
- URI 마지막 문자로 슬래시(/)를 포함하지 않는다. 분명한 URI를 만들어 통신하기 때문에 혼동을 주지 않도록 URI 경로의 마지막에는 사용X
```
http://api.com/food/meat/pork/  (X)
http://api.com/food/meat/pork   (O)
```
- 하이픈(-) 은 URI 가독성을 높이는데 사용.
- 밑줄(_)은 URI에 사용하지 않는다. 밑줄을 보기 어렵거나 문자가 가려지기도함.
- URI 경로는 소문자가 적합하다. 대소문자에 따라 다른 리소스로 인식함.
- 파일 확장자는 URI에 포함시키지 않는다
```
http://api.com/games/3/photo.png (X)
``` 
Accept header를 사용한다.
```
GET http://api.com/games/3/photo HTTP/1.1 Host: http://api.com Accept: image/png 
```
- 목록 형태의 리소스를 다루는 API는 복수형 명사
- 목록에서 특정 조건으로 필터링 : 쿼리 문자열
- id와 리소스 의외의 정보를 URI에 담으면 안된다
- URI는 정보의 자원을 표현해야한다.

## HTTP Method 별 자원의 상태
|        | 수행하기 전 | 반복 수행한 결과 | 리소스의 수 |
|--------|-------------|------------------|-------------|
| GET    | 같다        | 같다             | 같다        |
| POST   | 다르다      | 다르다           | 많아진다    |
| PUT    | 다르다      | 같다             | 같다        |
| DELETE | 다르다      | 다르다           | 적어진다    |

## API의 버져닝
트로이 헌트가 말하는 세 가지 틀린방법
- URL versioning
  : url에 버젼 명시해서 url 분리
- custom request header
  : request header에 버젼 넣기   
- content type
  : Accept Header에 버전 넣기