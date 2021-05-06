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

### HelloResponseDto 

* @Getter -> 필드에 get메소드 생성
* @RequiredArgsConstructor -> final필드에 대응하는 생성자 생성  

* assertThat, isEqualTo  
* @RequestParam -> 요청에서 넘긴 파라미터를 바인딩  

# 3장  
객체지향 패러다임을 Persistence영역에 적용해보자  
-> ORM을 활용해보자  
  
데이터 중심의 반복적인 CRUD작성에서 벗어나  
비즈니스로직에 집중할 수 있다.  

JPA는 자바 ORM의 표준 인터페이스 이며 구현체중 가장 활성화된것이 Hibernate  
그리고 구현체를 더 쉽게 사용할 수 있게 추상화한것이 Spring Data JPA이다.  
Data JPA를 사용함으로서 구현체에 종속되지 않으며 저장소도 교체 가능해진다.  

## 요구사항 분석

#### 게시판 기능

* 게시글 CRUD

#### 회원 기능

* 구글/네이버 로그인
* 로그인한 사용자 글 작성 권한
* 본인 작성 글에 대한 권한 관리

## Posts  

* @GeneratedValue Strategy  
* @NoArgsConstructor -> 프록시 생성을 위해 필수  
* 명확하지 않은 setter는 배제
* 생성자 or Builder
* Builder의 장점 -> 체인 메소드로 인자 순서에 상관없이 명확하게 삽입 가능  

* 쿼리 보는법 -> spring.jpa.show_sql=true  
* id 생성 전략은 DB와 generatedValue Strategy에 따름  

## 등록,수정,조회 API  

* Service는 트랜잭션, 도메인간 순서보장  
* 비즈니스처리는 Domain에서!
* 생성자주입 -> RequiredArgsConstructor
* @Transactional  
* MVC -> @PathVariable, @RequestBody, @RequestParam  
* 영속성 컨텍스트 -> 
* 더티 체킹  -> 

## JPA Auditing  

* @MappedSuperclass  
* @EntityListeners  

# 4장  

## 머스테치(mustache)  

* html은 위에서부터 코드가 실행되므로 css는 위에 js는 하단에 호출하는것이 좋다

* @Query로 JPQL 작성 가능
* 복잡한 쿼리는 queryDSL 추천
* 선언적 트랜잭션의 propagation, readonly의 효과,isolation  

#5장  

## 스프링 시큐리티, Oauth2

* 스프링 시큐리티는 인증과 인가에 관한 막강한 커스텀이 가능하지만 여전히 매우 복잡하다  
* 0Auth를 이용하면 이러한 복잡성을 믿을만한 서비스에 위임할 수 있다.
* OAuth ->  

## 구글 서비스 

* @Enumerated(EnumType.STRING) -> Enum은 String으로 저장해야 알아볼 수 있따.