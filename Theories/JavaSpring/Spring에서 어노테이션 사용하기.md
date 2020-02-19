# Spring에서 어노테이션 사용하기

Spring에서 bean등록을 따로 하지 않고 Annotation을 사용해서 DI를 사용 하면 contextloaderlistener가 로드 하는 config.xml이 복잡해지지 않고 더러워지지 않는다.

Annotation을 사용하기 위해서는 servlet-context.xml과 개발자가 지정한 config.xml파일, 그리고 Business Logic을 손봐주면 된다.



### application-config.xml

Spring 컨테이너를 실행 시키기 위한 xml파일이다. 



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	<context:component-scan base-package="com.clone_trello.CT"></context:component-scan>
</beans>                           
```

- Business Logic 에서 component 되어 있는 class를 찾기 위해 component-scan을 사용한다.
- base-package로는 패키지 구성중 3단계 까지 지정해준다.

>
>
>>
>>
>>>
>>>
>>>>초기에 com.clone_trello 두단계 까지만 지정해서 오류가 많이 났다...

### servlet-context.xml

DispatcherServlet이 요청 url과 매핑이 되어있는 service()를 찾아 servlet 객체를 만들어줄때 필요한 xml이다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context">
	
	<annotation-driven />
	<context:annotation-config/>
	
</beans:beans>

```

- `<annotation-driven />` , `	<context:annotation-config/>` 두가지 태그만 설정해주면 끝~~