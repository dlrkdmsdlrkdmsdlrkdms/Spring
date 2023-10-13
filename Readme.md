# Spring Framework

- [스프링 홈페이지](https://spring.io)
- 스프링은 자바를 조금 더 객체지향적으로 쉽고, 생산성 있게 만들어주는 프레임워크
- 스프링의 핵심 개념
  - IoC (Inversion Of Control) : 제어의 반전
  - DI (Dependency Injection) : 종속성 주입
- Spring Boot
  - 자바 프로그래밍(스프링) 작업에 접근하는 방식을 획기적으로 간소화
- [Spring Initilizr](https://start.spring.io)
  - Project (빌드 도구를 선택) : Gradle-Groovy
  - Language (JVM 언어를 선택) : Java
  - Spring Boot (스프링부트 버전을 선택)
    - 3버전 이후는 자바 17 버전 이상 사용
    - 3.1.4
  - Project Metadata
    - group : (일반적으로 도메인 주소를 뒤집어 사용)
    - artifact : 프로젝트 이름
    - packaging : Jar
    - Java : 17
  - Dependency
    - 선택하지 않음
  - GENERATE (생성하면 압축파일 다운로드)
- 압축받은 파일을 풀고
- -> IDE로 프로젝트 폴더를 열기

## 프로젝트 폴더 구조
```
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─busanit
    │  │          └─spring
    │  └─resources
    │
    └─test
        └─java
            └─com
                └─busanit
                    └─spring
│  .gitignore : 깃 저장소에서 제외될 목록
│  **build.gradle : 그레이들의 빌드 설정, 의존성**
│  settings.gradle : 멀티 프로젝트 설정 시 사용
```

- src : 소스코드가 위치하는 디렉토리
  - main : 주요 소스코드
    - **java : 자바 소스코드 파일이 위치**
      - 그룹명.아티팩트명
    - ~~(기타언어) kotlin : 다른 언어 소스파일이 위치~~
    - resources : 설정 파일, 이미지 파일 등
  - test : 테스트 코드가 위치
    - main과 유사한 폴더 구조를 가짐
- build : 빌드 시 생성되는 모든 파일 및 폴더
- gradle : 빌드 도구가 저장되는 디렉토리
- .gradle : 빌드 도구 캐시파일

  - 실행 설정
    - 파일 > 설정 > 빌드, 실행, 배포 > Gradle > Gradle 프로젝트 > 다음을 사용하여 빌드 및 실행 : IntelliJ IDEA
    - [메이븐 저장소](https://mvnrepository.com/)
      - 빌드 도구 설정 파일 의존성 추가
        - 그레이들 : build.gradle
      ```groovy
      dependencies {
          implementation 'org.springframework:spring-context:6.0.11'
      }
      ```
        - 메이븐 : pom.xml
      ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
            </dependency>
        </dependencies>
      ```

## 스프링 = 객체 컨테이너 = IoC 컨테이너
  - AnnotationConfigApplicationContext
    - 자바 애노테이션을 통해 빈 객체 설정 정보를 불러온다.
  - XmlConfigApplicationContext
    - XML을 통해서 빈(Bean) 객체 설정 정보를 가져온다.

## 스프링 IoC 컨테이너 용어
  - Bean : 빈 또는 빈 오브젝트. 스프링이 IoC방식으로 관리하는 오브젝트
    - 스프링에서 사용하는 모든 오브젝트가 Bean 아니다.
    - 스프링이 직접 생성과 제어를 담당하는 오브젝트
  - Bean Factory : 스프링의 IoC를 담당하는 핵심 컨테이너
    - 빈을 등록하고, 생성하고, 조회하고, 돌려주고, 부가적인 기능을 추가.
    - getBean() 등의 메소드가 정의되어 있다.
  - Application Context : 빈 팩토리를 확장한 IoC 컨테이너
    - 기본 기능은 Bean Factory와 동일 (상속)
    - 실제 스프링에서는 Context를 더 많이 사용 (빈 팩토리 용어로 부를 수 있음)
  - Configuration : 스프링 빈 팩토리가 IoC 적용하기 위한 메타 정보
    - 앱 오브젝트를 생성하고 구성할 때 사용
    - 애플리케이션의 전체 그림이 그려진 형상정보 또는 blueprint
  - Container : => 빈 팩토리, (앱 컨텍스트)를 가리키는 것
    - 스프링 컨테이너라고 부른다.
    - 스프링에 빈을 등록한다. => 환경 정보, 컨테이너에 객체 등록 
  - 스프링 프레임워크 :
    - 어플리케이션 컨텍스트를 포함한 모든 기능을 통틀어 말함.
  - 스프링의 삼각형
    - 제어의 역전(IoC) / 의존성 주입(DI)
    - AOP : 관점 지향 프로그래밍
      - 부가적인 기능을 독립적으로 모듈화하는 프로그래밍 모델
    - 서비스 추상화
      - 스프링은 환경이나, 서버, 특정 기술에 종속되지 않고
      - 유연한 애플리케이션을 만들 수 있다.

## 의존성 주입 (DI)
- 결론 : 생성자 주입방식을 선택하세요, (변경이 필요할때만 세터)
1. 생성자 주입 방식 (!)
   - 빈 객체를 생성하는 시점에 모든 의존 객체를 주입
   - 딱 한번만 호출되는 것이 보장된다.
   - 불변하고 필수적인 의존관계에서 사용
2. 세터 메서드 주입 방식
   - 세터 메서드를 통해 의존 객체를 주입
   - 선택하거나 변경 가능성이 있는 의존 관계에 사용
   - 주입할 대상이 없으면 오류가 발생 NPE
3. 필드 주입
   - 필드에서 바로 주입하는 방법.
   - DI 프레임워크(스프링)이 없으면 동작하지 않는다.
   - 코드가 간결해서 사용하고 싶은 유혹이 든다.
   - 테스트 등 실제 코드와 관계 없는 곳에서 사용
4. 일반 메서드 주입
   - 한번에 여러 필드를 주입할 수 있지만 잘 사용하지 않음.

## 설정 파일 관련
### 2개 이상의 설정파일을 사용하기 (AppConfig, Main 1, 2번)
- 수십개 또는 수백여개의 빈을 나누어서 영역별로 관리하기 위해 설정파일을 나누어서 관리한다.
- ApplicationContext에서 매개변수로 여러 설정파일 사용
### @Import 어노테이션으로 설정파일 가져오기 (AppContext, Main 3번)
- @Import({ 설정파일들... }) 을 통해 포함되는 설정파일들을 불러올 수 있다.

## getBean() 메서드 (AppContig, Main 4번)
- 매개변수로 빈 이름, 타입을 사용
- 빈 이름을 넣지 않고, 타입만으로도 빈을 찾을 수 있다.
- 하지만, 같은 타입의 빈 객체가 여러개 있을 경우
- NoUniqueBeanDefinitionException 이 발생한다.

## 주입 대상 객체를 빈 객체로 설정하지 않을 경우
- getBean()으로 불러오지 않는 대상 객체는 @Bean이 아니어도 주입은 할 수 있다.
- MemberPrinter가 @Bean이 아니라도, 해당 객체를 주입받은, MemberListPrinter와 MemberInfoPrinter는 정상 작동.
- @Bean으로 등록할 때는 스프링 컨테이너가 객체를 관리한다.
- 단순 객체 생성 외 다양한 기능 활용 시 Bean으로 등록한 객체가 아니면 스프링의 기능이 적용되지 않는다.
- MemberDao의 경우 @Bean을 해제해도, 주입은 할 수 있지만, 데이터를 온전히 보전하지 않았다.
- 의존 자동 주입 기능을 이용해서 주입 대상은 모두 스프링 빈으로 등록하는 추세.

## 의존 자동 주입
- @Autowired 애노테이션을 이용한 자동 주입
- 필드, 메서드, 파라미터, 생성자 등 다양한 의존 주입할 대상에 애노테이션을 사용
- 스프링 컨테이너에서 동일한 타입의 Bean 객체를 찾아 자동 주입
- 동일한 타입이 없을 경우 -> 예외발생
- 동일한 타입이 2개 이상 있을 경우 -> 예외발생
  - @Qualifier 애노테이션을 통해 동일한 타입이 2개 있을 경우 한정자를 선택할 수 있다.
  - @Primary 애노테이션을 통해 우선순위를 설정할 수 있다.
- Required 필수여부
  - 기본값이 true : 반드시 자동주입 대상 bean객체가 존재해야 한다.
  - required를 false로 지정할 경우, 필수적으로 자동 의존 주입 대상이 존재하지 않아도 익셉션이 발생하지 않느다.
    - Optional : 클래스를 통헤 null 예외 회피 가능
        - Nullable 어노테이션
- 자동 주입과 명시적 의존 주입의 관계
  - 자동 주입과 명식적 주입을 섞어 사용하는 경우, 자동 주입 우선
  - 명시적 의존 주입, 자동 의존 주입은 섞어서 사용하지 않는 편이 좋다.
  => 자동 주입을 사용하는 트렌드

## 컴포넌트 스캔 
- @Component 애노테이션을 붙인 클래스를 패키지 기준으로 검색해 스프링 빈으로 자동 등록
- 설정 클래스에서 @ComponentScan 애노테이션을 적용한다.
- 컴포넌트 스캔 범위 @ComponentScan 애노테이션 패키지 아래를 검색
  - basePackage로 스캔 범위를 설정할 수 있다.
- 컴포넌트 스캔 대상
  - @Component : 기본 스캔 대상 컴포넌트
  - @Controller : 스프링 MVC 컨트롤러 (Model view Controller)
  - @Service : 스프링 비즈니스 로직 담당하는 계층
  - @Repository : 스프링 데이터 접근 계층 사용
  - @Configuration : 스프링 설정 정보
- 컴포넌트 스캔 대상 범위 동일한 타입이 빈이 2가지 있을 경우
  - 충돌 발생
    - 필터로 예외처리
    - 컴포넌트의 이름 변경
    - 불필요할 경우 컴포넌트에서 제외
## 스프링 빈의 생명주기
- 스프링 컨테이너 생성  ->  스프링 빈 생성  ->  의존관계 주입  ->  초기화
->  사용(getbean)-> 소멸(destroy)  ->  스프링 컨테이너 종료
- 빈 생성 단계
  1. 자바 객체 생성 할 때 처럼 빈 객체 초기화
  2. Bean 객체 스캔하고 관련 프로퍼티 세팅
  3. afterPropertiesSet() 메서드 실행
  4. @initMethod 실행
- 빈 소멸 단계
  1. @PreDestory 실행
  2. destroy()실행
## 생명주기 콜백
- 인터페이스 구현 : InitializingBean, DisposableBean
- 어노테이션 파라미터 사용 : @initMethod, @PreDestroy

### Bean의 관리 범위(스코프)
- singleton : 기본범위, 모든 스프링 컨테이너에 하나의 객체만 생성
- prototype : 객체별로 각각 생성
- request : HTTP 요청마다 하나의 빈
- session : HTTP 세션마다 하나의 빈
- application : 서블릿 컨테이너마다 하나의 빈
- websocket :  웹 소켓마다 하나의 빈
- [스프링 공식문서]

## AOP(Aspect Oriented Programming)
- 핵심 기능과 분리된 공통 부가기능을 관심사(Aspect)로 정의하고 모듈로 만든 것
- 객체지향 프로그래밍을 보완하는 수단
- 핵심 용어
  1. Advice (언제) : 호출할지 정의하는 것
    - *Around : 메서드 실행 전 후 *
    - Before : 메서드 호출 전
    - After : 메소드 실행 후 반드시 실행 (finally)
    - After Returning : 조인포인트 정상 완료 시 (try)
    - After Throwing : 예외 발생 시 실행 (exception)
  2. JoinPoint (어디에) : 적용 가능한 지점
  3. PointCut : 조인포인트 중 적용될 지점을 선별
  4. Weaving : Advice를 핵심 로직에 적용하는 것
  5. Aspect (관심사) : 공통으로 적용될 기능(관심사) 
- 스프링 설정 정보 @EnableAspectJAturoProxy
  - 자동으로 등록된 빈에서 @Aspect를 검색해 프록시를 생성해준다.
  - Proxy(대리자)는 원본 객체 대신 프록시 객체를 가져와 메소드를 호출할 때 Aspect를 적용
## DataBase 연결
- 의존성 연결
    ```
 	// MySQL 연결 JDBC 드라이버
     implementation 'mysql:mysql-connector-java:8.0.33'
     // DB 커넥션 풀 제공
     implementation 'org.apache.tomcat:tomcat-jdbc'
     // jdbcTemplate 등 JDBC 연동 기능 제공
     implementation 'org.springframework:spring-jdbc'
     ```
  - 자바 프로그램과 DBMS 연결 시간이 길기 때문에 성능에 영향을 줄 수 있다.
    커넥션 풀에서 커넥션을 미리 만들어서 가져와 사용한 후, 커넥션 풀에 반납해서, 동시 접속자가 많더라도 커넥션을 생성하지 않고 
    커넥션 풀에서 가져와서 사용할 수 있다.
  - 많이 사용하는 커넥션 풀 라이브러리 HikariCP, Tomcat JDBC
## JDBC 템플릿
- JdbcTemplate을 이용한 조회 쿼리
  - query(String sql, RowMapper<T>, Objects... args )
    - 첫 번째 매개변수 SQL문
    - 두 번째 매개변수 쿼리 결과와자바 객체의 매핑
      - SQL문 결과 셋과 자바 객체(Member)를 매핑
      - SQL 결과 컬럼과 자바 객체 필드의 타입을 매핑
      - RowMapper 인터페이스의 구현 클래스  ->  람다식 변경 가능
    - 세 번째 매개변수(필수아님) ? 에 들어갈 인자
    - Update(sql, args...)
      - DB에서 삽입, 삭제, 수정 SQL 문을 사용할 경우
        - 첫번째 파라미터 SQL문
        - 두번째 파라미터부터 해당 인자
    - PrepareStatementCreator
      -  PrepareStatementfmf SQL문 대신 만들어서 파라미터를 삽입할 수 있다.
    - KeyHolder
      - DB에서 자동으로 생성된 키 값을 구하기 위해 사용
      - PrepareStatement에서 두번째 파라미터 값으로 String[]{"ID"}를 주어서
    DB에서 생성된 ID 컬럼 값을 가져올 수 있다.

  ## Spring에서 트랜잭션 처리
- 두 개 이상의 쿼리문을 한 작업으로 실행해야 할 때, 트랜잭션으로 논리적으로 하나의 작얿으로 묶어준다.
- 일반적으로 Service 계츧에서 여러 쿼리문이 묶인다.
- @Transaction 애노테이션을 통해 트랜잭션 범위 지정
- 핵심 기술
  1. 트랜잭션 동기화
  2. 트랜잭션 추상화
  3. AOP를 이용한 트랜잭션 관심사 분리 
- PlatformTransactionManager를 통해 관리
- 프록시 객체를 통해 커밋, 롤백 수행
- @Transactional의 주요 속성
  - Propagation : 트랜잭션의 전파타입 REQUIRED
    - REQUIRED : 현재 진행 중인 트랜잭션이 존재하면, 해당 트랜잭션 사용하고, 
                존재하지 않으면 새로운 트랜잭션 생성
    - REQUIRES_NEW : 항상 새로운 트랜잭션 필요
  - isolation : 트랜잭션의 격리레벨, 기본값 : DEFAULT
    - READ_UNCOMMITED : 다른 트랜잭션이 커밋하지 않은 데이터를 읽을 수 있게 해준다.
    - SERIALIZABLE : 동일한 데이터에 대해서 두 개 이상 트랜잭션 수행 불가
    - readOnly : true로 설정할 경우 INSERT, UPDATE, DELETE 불가 (SELECT 만 가능)
  ## SPRING MVC
- Model
- view
- Controller
- 프론트 콘트롤러 패턴
  - DispatcherServlet : 웹 브라우저로부터 요청이 들어오면, 앞 단에서 모든 연결과 요청을 담당
    - 요청을 처리하기 위한 컨트롤러를 검색
      - HandlerMapping : 빈 객체를 통해 컨트롤러 검색
      - (Controller)
      - HandlerAdapter : 컨트롤러 메소드에 알맞은 메소드 호출
      - (Model)
      - ViewResolver : 이름에 해당하는 View 객체를 찾아서 생성, 리턴
      - (View)

