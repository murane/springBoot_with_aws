# 1장  

buildscrpit란?  
ctrl + space로 추론  
jcenter, mavenCentral 차이  

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
