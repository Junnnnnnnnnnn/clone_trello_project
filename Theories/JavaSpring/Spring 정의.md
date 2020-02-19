# Spring 

## Spring의 정의

- 자바 **엔터프라이즈** 개발을 편하게 해주는 오픈 소스 경량급 에플리케이션 프레임워크

> #### 엔터프라이즈란
>
> 엔터프라이즈급 어플리케이션을 개발하기 위해선 4가지 조건을 충족 해야한다.
>
> - 기존 데이터 베이스 및 툴과 호환 가능
> - 특정 부서의 요구 사항에 맞게 사용자 정의 가능
> - 비즈니스 요구 사항에 맞게 확장 가능
> - 외부 리스크 및 데이터 유출로부터 보완

### 1.  애플리케이션 프레임워크

- 일반적으로 **라이브러리**나 **프레임워크**는 특정 업무 분야나 한가지 기술에 특화된 **목표**를 가지고 만든다 예를들어
  1. 웹 계층을 MVC 구조로 손쉽게 만들 수 있게 함
  2. 포맷과 출력장치를 쉽게 변경할 수 있는 app 로그 기능 제공
  3. 간단한 설정만은로 **RDB**와 **자바 오브젝트**를 매핑 해주는 **ORM** 기술을 제공

그래서! **프레임워크**는 애플리케이션 특정 계충에서 주로 **한가지** 분야에 집중 된다. 

<font color="red">그러나 spring은 이와다르게 **애플리케이션 프레임워크** 라는 특징을 가지고 있다.</font>

#### 	즉 애플리케이션 프레임워크는 애플리케이션 개발의 전 과정을 빠르고 편리하며 효율적으로 진행하는데 일차적인 목표를 두는 프레임 워크



### 2.  경량급

스프링의 정의 중 경량급에 해당하는 장점

- 스프링은 가장 단순한 서버환경인 **톰캣**에서도 완벽하게 동작한다
- **서블릿** 컨테이만으로 충분하니 복잡한 기능을 가진 WAS를 사용하지 않아도 된다.
- 코드가 **여타 프레임워크**에서 동작하기 위해 만들어진 코드에 비해 **작고 단순하다**
  - 작은이유
    - 프레임워크와 서버환경에 의존적인 부분을 제거해줬기 때문

이로써 엔터프라이즈급 개발을 **Spring**만으로 대부분 사용할 수 있다.

## spring 컨테이너란?

- 컨테이너의 사전적 의미는 무언가를 담는 용기이다. 그래서 **객체관리**를 주로 수행하는 그릇이라고 이해 할 수 있다 예를들어
  1. 빈의 **생성**과 **관계**
  2. **생명 주기등을 권장한다.**

### 두개의 컨테이너

**spring** 컨테이너는 대표적으로 **두가지** 컨테이너로 나뉜다.

1. **Spring Container**
   1. **BeanFactory**
      - 단순히 컨테이너에서 **객체**를 **생성하고** **DI를 처리**해주는 기능만을 제공한다.
      - Bean을 **등록, 생성, 조회, 반환** 관리한다.
      - Bean의 정의는 즉시 로딩하는 반면 빈 자체가 필요하게 되기 전까지는 인스턴스화 하지 않음
   2. **ApplicationContext**
      - **BenaFactory를** 상속했고 보다 더 많은 기능을 제공한다
        - 국제화가 지원되는 텍스트 메시지를 관리
        - 이미지같은 파일 자원을 로드할 수 있는포괄적인 방법을 제공
        - 리스너로 등록된 빈에게 이벤트 발생을 알려줌

따라서 대부분은 App에서는 **BeanFactory** 보다는 **ApplicationContext**를 사용하는 것이 좋다.

3. **ServletContainer**
   - **서블릿** **컨테이너는** 개발자가 웹서버와 **통신하기** 위하여 **소켓을** 생성하고, **특정포드에** 리스닝하고, **스트림을** 생성하는 등의 복잡한 일들을 할 필요가 **없게** 해준다
   - **컨테이너는** **servlet의** **생성부터** **소멸까지의** 일련의 과정을 **관리한다**. 서블릿 컨테이너는 **요청이** 들어올 때마다 새로운 자바 **스레드를** 만든다. 우리가 알고 있는 대표적인 **Serviet** **Container가** **Tomcat** 이다. 톰켓 같은 **was가** java파일을 **컴파일해서** Class로 만들고 메모리에 올려 **servlet객체를** 만든다

>### Servlet 동작 과정
>
>![](.\readme\studyIMG2.png)
>
>1. 사용자가 **URL**을 클릭하면 **HTTP** **Request를** **Servlet** **Container(tomcat)에** 보낸다
>2. **Servlet** **Container는** **HttpServletRequest**, **HttpServletResponse** 두 객체를 생성한다.
>3. 사용자가 요청한 URL을 분석하여 어느 서블릿에 대한 요청인지 찾는다.
>4. 컨테이너는 서블릿 service() 메소드를 호출하며, POST, GET여부에 따라 doGET() 또는 doPost()가 호출된다.
>5. doGET() or doPost() 메소드는 동적인 페이지를 생성한 후 HttpServeltResponcs객체에 응답을 보낸다.
>6. 응답이 완료 되면 HttpServletRequest, HttpServletResponse 두객체를 소멸시킨다.

### IoC와 DI

- IoC
  - 개발자는 JAVA 코딩시 **New연산자**, **인터페이스 호출**, **팩토리 호출 방식**으로 객체를 생성하고 소멸 시킨다. IoC란 인스턴스의 생성부터 소멸까지의 객체 생명 주기 관리를 개발자가 하는 대신 **스프링(컨테이너)가 관리한다**
- DI
  - IoC를 **실제**로 구현하는 방법으로서 의존성 있는 컴포넌트들 간의 관계를 개발자가 **직접 코드로 명시하지 않고** 컨테이너인 **Spring**이 런타임에 찾아서 연결 해주게 하는 것이다.

**Spring** **Container는** Bean들의 생명주기를 관리한다 그 Bean들을 관리하기 위해 **IoC를** 사용한다. 이 두개의 컨테이너 **BeanFactory와** **ApplicationContext로** 의존성 주입(**DI**)된 Bean들을 **제어하고** **관리할** 수 있다. 

### 웹어플리케이션 동작 원리

![](.\readme\studyIMG.png)

1. 웹 어플리에키션이 실행 되면 -> **Tomcat에** 의해 **web.xml이** 로딩 된다.
2. **web.xml에** 등록되어 있는 **ContextLoaderListener**(Java Class)가 생성된다. **ContextLoaderListener클래스는** **ServletContextListener** 인터페이스를 구현하고 있으며, **ApplicationContext를** 생성하는 역할을 수행한다.
3. 생성된 **ContextLoaderListener는** **Spring** **Cantainer를** 구동시키기위해  **root**-**context.xml을** 로딩한다
4. **root**-**context.xml에** 등록되어 있는 **Spring** **Container가** 구동된다. 이 때 개발자가 작성한 **로직**(**Servies** , **Repository** , **Controller** 등)에 대한 부분과 **DAO**, **VO** 객체들이 생성된다

> ## DAO 와 DTO(VO) 
>
> ### DAO란
>
> - Data Access Object의 약잘로 간단히  Database의 data에 접근을 위한 객체
> - Database에 접근을 하기 위한 로직과 , 비스니스 로직(**Servies** , **Repository** , **Controller** 등)을 분리하기 위해 사용한다.
>
> ### DAO 설명
>
> 원래 웹서버는 DB와 연결하기 위해 **매번** 커넥션 객체를 생성하는데, 이것을 해결하기 위하여 **컨넥션 풀**이 등장했다. 
>
> > #### ConnectionPool이란
> >
> > **connection** 객체를 미리 **만들어** 놓고 가져다 쓰는 것과 다 쓰고 난후 **반환해** 놓는 것이 주 목적 그러나 유저 **한명이** 접속해서 한번에 **하나의** **커넥션**(예를들어 검색하기, 이미지 클릭하기)만 일으키지 않고 한번에 **여러가지** 행동들을 한다
> >
> > 1. **게시판을 읽는다고 가정을 하자**
> >
> > 게시판만 보더라도 **목록볼때** 한번, **글읽을때마다** 한번, **글쓸때** 한번.. 등 엄청 나게 많은 커넥션이 일어나 **오버헤드가** 발새하기 십상이다.
> >
> > 그래서!
> >
> > **DAO를** 통해 **DB와** **비즈니스** 로직을 분리해 DB전용 객체가 있다면 오버헤드를 **효율적으로** 처리할 수 있다.
>
> DAO의 흐름은 다음과 같다
>
> ![](.\readme\studyIMG4.jpg)
>
> ### DTO란
>
> **DTO는** **VO로** 바꿔 말할 수 있는데 계층간에 **데이터교환** 즉
>
> - **Controller, View , Business Layer, Persistent Layer**
>
> 을 위한 자바빈즈를 말한다
>
> 일반적으로  **DTO는** 로직을 가지고 있지 않는 **순수한** **데이터** 객체이며 속성과 그 속성에 접근하기 위한 **getter**, **setter** 메소드만 가진 클래스를 말한다.
>
> 다른과 같은 예제가 대표적인 DTO이다
>
> ```java
> public class TestDto {
> 
>     private String name;
>     private int value;
>     private String data;
> 
>     public String getName() {
>         return name;
>     }
> 
>     public void setName(String name) {
>         this.name = name;
>     }
> 
>     public int getValue() {
>         return value;
>     }
> 
>     public void setValue(int value) {
>         this.value = value;
>     }
> 
>     public String getData() {
>         return data;
>     }
> 
>     public void setData(String data) {
>         this.data = data;
>     }
> }
> ```
>
> 

5. **클라이언트로**(**사용자**)부터 웹 어플리케이션의 요청이 온다.
6. **DispatcherServlet**(**Servlet**)이 생성된다. **DispatcherServlet은** **FrontController의** 역할을 수행한다. 클라이언트로부터 요청온 메시지를 분석하여 알맞은 **PageController에게** 전달하고 응답을 받아 요청에 따른 **응답을** 어떻게 할지 결정만 한다. 실질적인 작업은  PageController 이루어지기 때문에 이러한 클래스를 HandlerMapping, ViewResolver ndlerMapping, ViewResolver 클래스라고 한다.
7. **DispatcherServlet**(**Servlet**)은 **servlet**-**context.xml을** 로딩한다.
8. 두번째 **Spring** **Container가** 구동되며 응답에 맞는 **PageController** 들이 동작한다. 이 때 **첫번째** **Spring** **Container가** 구동되면서  생성된 **DAO**, **BO**, **Servicelmpl** 클래스들과 협업하여 알맞은 작업을 처리하게 된다.
