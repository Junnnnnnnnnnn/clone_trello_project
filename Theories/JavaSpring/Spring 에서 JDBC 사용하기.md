# Spring 에서 JDBC 사용하기

## pom.xml

**Spring** 에서 **jdbc**를 사용하기 위해서는 **pom.xml**에 **jbdc** 라이브러리와 사용할 **db** 라이브러리를 선언해줘야 한다

```xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
```

- 위와 같이 jdbc 라이브러리를 dependency해준다.
- `<version>` 에 기입되어 있는 것을 placeholder 라고 한다

>### placeholder
>
>- **placeholder**란 사용하고 싶은 정보를 미리 선언해주고 그 정보를 **${contents} <- contents** 부분에 기입하면 사용가능 하다.
>- **변수** 사용하듯 사용하면 된다.
>
>#### 장점
>
>- 장점으로는 **변수처럼 사용할 수 있어서** 수정할 곳이 여러곳이라면 변수 하나만 수정해주면 한번에 바뀌기 때문에 **수정에 용이** 하다.

jdbc 라이브러리를 선언했으면 사용 db 라이브러리를 선언해야한다 필자는 oracle db를 사용했다.

```xml
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc7</artifactId>
			<version>12.1.0.2</version>
		</dependency>
```

- 위 코드를 그대로 작성한다면 **에러가** 날 것이다. 에러가 난다면 ORACLE JDBC Repository를 등록해주지 못해서 이다. 다음 코드를 작성하자.

```xml
	<repositories>
		<repository>
			<id>oracle</id>
			<name>ORACLE JDBC Repository</name>
			<url>http://maven.jahia.org/maven2</url>
		</repository>
	</repositories>
```

- 다음과 같이 repository를 등록해주면 **oracle db** 와 **jdbc**을 사용할 수 있다.

## application-config

라이브러리에 선언한 **jdbc**를 사용하기 위해선 **bean**등록을 해줘야 한다 왜냐하면 IOC컨테이너에서 의존성 주입(**DI**)을 하기 위해서는 bean에서 생성이 되어야 하기 때문이다.

```xml
<context:property-placeholder location="classpath:database/jdbc.properties"/>	

<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="${jdbc.driverClassName}"/>	
		<property name="url" value="${jdbc.url}"/>
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
</bean>
```

- **dataSource**는 db와의 연결을 위한 **connetion** 정보를 담고 있기 때문에 bean등록을 해준다.
- **property**에 들어갈 value는 placeholder 태그를 통해 location에 등록한 jdbc.properties에 작성해 놓은 정보들을 가져다 사용한다.

dataSource를 등록했으면 Business Logic에서 사용할 jdbcTemplate를 bean등록 해준다.

```xml
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
```

- bean에 등록한 dataSource객체의 setDataSource를 통해 주입을 해준다.
- 특정객체의 **setter함수**를 **property**를 이용해 사용하기 위해서는  **set을 없에고** 첫번째 함수명을 **소문자**로 바꾸고 ref에 작성해준다.

## web.xml

지금까지 설정한 **application-config.xml**을 **client**가 url요청시 사용해야 함으로 **ContextConfigLocation**으로 로드를 시켜줘야 한다. 왜냐하면 **DispatherServlet**으로 요청 url에 대한 servlet**분류를** 하기 전에  **Business** **Logic**을 정의한 스프링 설정 파일을 로드하기 때문!

```xml
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>classpath:spring/application-config.xml</param-value>
</context-param>
```

- **Spring Container** 에 정의되어 있는 **Business login**을 사용하기 위해서는 **application-config.xml**를 로드 시켜준다.

## Business Login

### .controller

**dispatcherservlet**에서 요청을 받으면 분류를 통해 해당 **servlet.service()**를 실행해 주는 곳이다. 

client가 로그인을 했을때 로그인 아이디가 db에 있다면 result 페이지를 , db에 없다면 resultFalse 페이지를 응답해주는 로직이다.

기본적으로 로그인을 했을때 db에 해당 유저가 없으면 회원가입을 해야하기 때문에 꼭 필요하 작업이라고 생각했다.

```java
@Controller
public class TrelloController {
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String result(@RequestParam(value = "ID", required = false) String uname, Model model) {
		//System.out.println("서버");
		//System.out.println(loginService.SearchInfo(uname) + "서버");
		if(loginService.SearchInfo(uname) == true) {
			return "result";
		}
		else {
			return "resultFalse";
		}
	}	
}
```

- @RequestMapping으로 요청 url이 /login 이라면 result 함수를 실행 시킨다.
- 일단 우리는 client에서 작성한 id와 password 데이터를 가져와야 하기 때문에 jsp에서 `<form>` 태그를 사용해서 `<input>`태그의  name값을 넘겨준다. 해당 유저의 data유무를 알기 위해서는 중복값이 없는 ID를 사용하면 되므로  `@RequestParam()`를 통해 id값 하나만 받아 온다.
- `@RequestParam() `에 들어갈 인자로는 `value` , `required`가 있고 `@RequestParam() ` 바로 뒤에 변수 명을 선언해준다. `requred`를 **false**로 선언해주면 해당 value값이 없다면 BadRequest라는 http 4** 에러가 나는데 그것을 발생하지 않게 한다.
- 의존성 주입으로 선언한 **loginService**의 SearchInfo 메서드를 실행 시키고 return값으로 true를 받는다면 result.jsp , 아니면 resultFalse.jsp를 web.xml을 통해 client에 응답한다.

### .Service

Service는 Repository에 정의되어있는 메서드를 실행시킨다 Repository는 주로 db와의 작업을 하기위한 Component이다.

```java
@Service
public class LoginService implements LoginServiceInterface {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Override
	public boolean SearchInfo(String uname) {
		boolean result = loginRepository.SearchInfo(uname);
		return result;
	}
}

```

- db와의 데이터 처리를 위한 **Repository**를 DI하고 해당 클래스의 함수인 SerchInfo를 실행시킨다. return type은 **boolean**이다.

### .Repository

앞서 언급한 것과 같이 Repository는 db와의 데이터를 주고 받는 역할을 한다 (쉽게 말해서 ㅎㅎ)

jdbc 객체를 사용하는 곳이기도 하다.

```java
@Repository
public class LoginRepository implements LoginRepositoryInterface{

	@Autowired
	JdbcTemplate jdbc;
	private class loginMapper implements RowMapper<LoginVO>{
	      @Override
	      public LoginVO mapRow(ResultSet rs, int count) throws SQLException {
	    	  LoginVO loginVO = new LoginVO();
	    	  
	    	  loginVO.setU_id(rs.getInt("user_uid"));
	    	  loginVO.setId(rs.getString("uname"));
	    	  loginVO.setPassword(rs.getString("pw"));	    	  
	         return loginVO;
	      }		
	}
	@Override
	public boolean SearchInfo(String uname) {
		String sql = "select * from loginTable where uname = ?";
		System.out.println("=====");
		LoginVO db_LoginVO = jdbc.queryForObject(sql, new loginMapper() , uname);
		String input_uname = uname;
		String db_uname = db_LoginVO.getId();
		System.out.println("db");
		System.out.println(db_LoginVO.getId());
		System.out.println(uname);		
		
		if(db_uname.equals(input_uname)) {
			System.out.println("성공");
			return true;			
		}
		return false;
	}
}
```

- **jdbc**에서 제공해주는 **RowMapper**를 사용하여 원하는 **VO객체에 db에서 가져온 데이터**를 **set**해주는 기능이다. 해당 클래스의 핵심 메서드인 **mapRow**는 매게변수로 **ResultSet**을 가지는데 이는 sql 쿼리 문으로 **Select**로 시작하는 쿼리문을 **db**에 넘기면 해당 **table**의 **데이터가** **ResultSet**에 저장이 된다.
- 저장된 rs를 이용해 loginVO에 user 정보를 저장시킨다.
- 다음 메서드로 **SerchInfo()**는 파라미터로 넘어온 **uname** 이 db에 있으면 ture , 없으면 false를 반환하는 메서드이다.
- sql문은 jdbc.queryForObject를 통해 db에 보내지고 쿼리문 수행후 해당 데이터를 가져와 db_LoginVO에 저장이 된다. 그때 타입은 LoginVO이다. **만약 해당 데이터가 없다면 EmptyResultDataAccessException에러가 생기게 되는데 queryForObject은 데이터가 없으면 0을 리턴하고 에러페이지를 띄운다. (이해한다고 고생..)**
- `if(db_uname.equals(input_uname))` 에서 equals를 사용한 문자열 비교를 했다. 그이유는 밑과 같다

>### java 문자열 비교
>
>- str1 == str2
>  - 위와 같은 방식은 str의 주소값을 비교하는 것이다
>  - 따라서 위 코드에 `input_uname` 와 `db_uname` 를 비교한다면 바라보고 있는 주소값이 다르기 때문에 false를 반환한다 (이것도 고생...)
>- srt1.equals(srt2)
>  - 위와 같은 방식은 str 값 자체를 비교하는 것이다.
>  - 주소값을 바라보고 있지 않기 때문에 문자열이 같다면 ture를 반환 한다.









>
>
>>
>>
>>>
>>>
>>>발생한 오류 
>>>
>>>**ORA-01756: quoted string not properly terminated** 쿼리문에 '를 빼먹음
>>>
>>>**EmptyResultDataAccessException** 반환 데이터가 없다면 0을 출력