# jdbc통해 input값 db에 insert하기

회원가입을 통한 회원 id와 password를 db에 넣는 작업을 할 것이다.

db와의 데이터 처리를 하기위해 **Repository**에 **business logic**을 작성해준다.

## SignUpRepository

```java
@Repository
public class SignUpRepository implements SignUpRepositoryInterface{

	@Autowired
	JdbcTemplate jdbc;
	
	public void InsertInfo(String id , String password) {
		String sql = "insert into loginTable values(seq_loginno.NEXTVAL,?,?)";
		String commit = "commit";
		jdbc.update(sql , id , password);
		jdbc.update(commit);
	}
}
```

- String sql에 들어가는 `?` 는 jdbc.update(sql , 매개변수1 , 매개변수2) 매개변수에 데이터를 입력하면 자동으로 맵핑이 되어 sql에 `?` 대신 데이터가 들어간다.
- insert로 db에 데이터가 입력됬다면 commit을 해줘야 한다. 그러면 db에 데이터가 업데이트만 되는 것이 아니라 실제 db에 데이터가 저장이 된다.



## TrelloCotroller.java

```java
@RequestMapping(value = "/signUpResult", method = RequestMethod.GET)
public String SignUp(@RequestParam(value = "id" , required = false)String id,@RequestParam(value = "PW" , required = false)String pw) {
	
	signUpService.InsertInfo(id, pw);
	return "result";
}
```
- `@RequestMapping` value를 통해 요청 url이 trello/signResult 이 들어온다면 아래 함수를 실행한다.
- SignUp함수에 매개변수로 `@RequestParam`을 통해 브라우저에서 넘겨온 값들을 받는다.
- 받은 값들을 변수에 대입하고 InsertInfo의 매개변수로 넣어주면 Repository에 있는 insert sql문에 실행되고 return값으로 result.jsp를 web.xml을 통해 클라이언트에게 보여준다.

