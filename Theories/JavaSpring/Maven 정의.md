# Maven

## Maven이란?

- Maven은 **자바 프로젝트 빌드를 자동화 해주는 빌드 툴** 이다. 즉, 자바 소스를 **compile**하고 **package**해서 전개(deploy)하는 일을 자동화 해주는 것이다.

## Maven이 참조하는 설정 파일

- **Maven** 전체를 보기보다 프로그래밍에 직접적인 연관이 잇는 두 개의 설정 파일을 알아보면 된다.

1. settings.xml
   1. **settings.xml**은 **maven tool **자체에 관련된 설정을 담당한다. **Maven **자체에 설정 값을 바꾸는 일은 일단 잘 없으므로 넘어가고 기획한데로 **porm.xml**을 살펴본다
2. pom.xml
   1. 하나의 자바 프로젝트에 빌드 툴로 **Maven**을 설정했다면 최상위 디렉토리에 **porm.xml** 파일 생긴다.
   2. **pom.xml**은 [POM](https://expert0226.tistory.com/189)을 설정하는 부분으로 프로젝트 내 빌드 옵션을 설정하는 부분이다. 

> #### POM이란?
>
> - Project Object Model이라는 개념을 바탕으로 프로젝트 [의존성 관리]([https://medium.com/@jang.wangsu/di-dependency-injection-%EC%9D%B4%EB%9E%80-1b12fdefec4f](https://medium.com/@jang.wangsu/di-dependency-injection-이란-1b12fdefec4f)), **라이브러리 관리**,**프로젝트 생명주기 관리** 등을 제공하는 프로젝트 관리 도구이다.
> - 다른 빌드 도구와는 다르게 **선언적 접근 방법**을 사용한다. 빌드 프로세스를 **이벤트** 단위로 기술하는 것이 아니라, 프로젝트를 **설명**하고  프로젝트 구조에 대해서 **정의**하고 이와 관련됭 **정보**들을 기술하는 형태를 가짐
>
> #### 의존성이란?
>
> - **Dependency**라고도 부르는데 기본적인 정의는 3가지로 나뉜다
>   1. 코드에서 두 모듈 간의 연결
>   2. 객체지향 언어에서는 두 클래스 간의 관계라고도 말함.
>   3. **일반적으로 둘 중 하나가 다른 하나를 어떤 용도를 위해 사용함**
>
> 그러나 **단점**이 생기기도 하는데
>
> - 하나의 모듈이 바뀌면 읜존한 다른 모듈까지 변경이 이루어진다. <- 개인적으로 조금 귀찮은 문제
>
> 이러한 정의를 기반으로 탄생한 기술이 있는데 **DI**라고도 하고 [의존성 주입]([https://ko.wikipedia.org/wiki/%EC%9D%98%EC%A1%B4%EC%84%B1_%EC%A3%BC%EC%9E%85](https://ko.wikipedia.org/wiki/의존성_주입))이라고도 한다
>
> #### 의존성 주입 (DI)
>
> - DI는 프로그래밍에서(**JAVA Spring**) 구성요소간의 의존 관계가 소스코드 내부가 아닌 외부의 설정 파일 등을 통해 정의되게 하는 기술이다.
>
> 장점도 여러가지다!
>
> 1. **의존 관계 설정이 컴파일시가 아닌 실행시에 이루어져 모듈간의 결합도를 낮출 수 있다**
>
> **컴파일시 이루어 지는 경우**
>
> ```java
> ... 중락 ...
> class Aclass(){
> 	int a;
> 	int b;	
> 	Aclass(){}
> }
> Aclass a = new Aclass() //컴파일시 이루어 지는 경우
> ```
>
> 실행시 이루어지는 경우는 **Maven**에 필요한 모듈이 있으므로 **new**을 할 필요가 없어진다.
>
> 2. 코드 재사용을 높여서 작성된 모듈을 여러 곳에서 소스코드의 수정 없이 사용할 수 있다.

## Maven 프로젝트 정보

- pom.xml 소스코드

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
 
    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>
 
    <name>demo</name>
    <description>Demo project for Spring Boot</description>
 
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
 
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>
 
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
 
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
 
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>


출처: https://jeong-pro.tistory.com/168 [기본기를 쌓는 정아마추어 코딩블로그]
```

맨 처음부터 코드 분석을 한다면

1. `<modelVersion>4.0.0</modelVersion>` : 4.0.0이라고 서있는 것은 maven의 pom.xml의 모델 버전이다.
2. `<groupId>com.example</groupId>`: 프로젝트를 생성한 조직 또는 그룹명으로 보통, **URL의 역순으로 지정한다.** 
3. `<artifactId>demo</artifactId>`: 프로젝트에서 생성되는 기본 아티팩트의 **고유 이름이다.**
4. `    <version>0.0.1-SNAPSHOT</version>`: 애플리케이션의 버전, 접미사로 SNAPSHOT이 붙으면 아직 **개발단계**라는 의미이며, 메이븐에서 라이브러리를 관리하는 방식이 다르다고 한다.
5. `<packaging>`: jar, war, ear,pom등 패키지 유형을 나타낸다.
6. `<name>`: 프로젝트 명
7. `<description>`: 프로젝트 설명
8. `<url>`: 프로젝트를 찾을 수 있는  URL

라는 **기본구조** 가 있고

추가적으로

```xml
<profiles>
  <profile>
   <id>dev</id>
   <properties>
    <java.version>1.8</java.version>
   </properties>
  </profile>
  <profile>
   <id>prod</id>
   <properties>
    <java.version>1.9</java.version>
   </properties>
  </profile>
</profiles>
```

1. `<properties>`: pom.xml에서 중복해서 사용되는 설정(상수) 값들을 지정해놓는 부분. 다른 위치에서 **${..}** 로 표기해서 사용할 수 있다.
2. `<profiles>`: dev,prod 이런식으로 개발할 때, 릴리즈할 때를 나눠야할 필요가 있는 설정 값은 profiles로 설장 할 수 있다.

## 의존성 라이브러리 정보

- 의존성 라이브러리 정보를 적는데 최소한 **3가지** 정보가 필요하다
  1. gruopId
  2. arifactId
  3. version
- 스프링 부트는 **version**은 따로 지정할 필요가 없다 