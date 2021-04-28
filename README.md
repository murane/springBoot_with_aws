# 1장  

* buildscrpit란?  
* ctrl + space로 추론  
* jcenter, mavenCentral 차이  

# 2장

### main 생성
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
@SpringBootApplication 어노테이션을 통해 
자동으로 설정을 해주고 컴포넌트 스캔의 기준점이 된다.

SpringApplcation.run으로 내장 톰캣을 실행한다.

### /hello 컨트롤러

* @RestController -> view로의 연결을 Rest로 바꿔서 json을 반환하게 한다.
* @GetMapping -> **Mapping을 통해 특정 http메소드를 라우팅한다.


* @RunWith(SpringRunner.class) -> Junit4일경우 필요하다  
* @WebMvcTest -> @SpringBootTest는 빈을 모두 띄우기 때문에 
  mvc와 관련된 controller,controllerAdvice,filter,interceptor
  ,webMvcConfigurer등의 빈을 띄워주는 WebMvcTest를 슬라이스 테스트에 사용한다.  
* MockMvc -> perform으로 요청을 보내고 Expect로 응답과 body를 검증한다.