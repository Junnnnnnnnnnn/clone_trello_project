# 트렐로 복제 - 전현진, 정혁진, 정소현

1. ### 목적

   - 트렐로를 복제를 통해 전반적인 웹의 흐름을 공부한다.

   ### 사용 툴

   1. 프론트
      - htel , css , JS , React ,JQuery, 
   2. 서버
      1. sevelet , spring - **3.9.11 RELEASE**(maven - **3.6.2**), tomcat(**8.5**)
   3. 백
      1. RDBMS(oracle db)  - ora11g_xe

   ### 웹 페이지 종류

   1. main.html
   2. login.html
   3. join.html
   4. content.html
   5. board.htm

   

   

   

   -------------

   #### 진행사항

   maven 설치중 오류 발생 -- 2020.01.10

   - 상황
     - sts와 eclipse를 설치 완료하고 maven과의 연동을 함
   - 문제
     - maven 설치를 sts가 아닌 eclipse에 하는데 무한 다운 에러가 생겨서 고민
   - 해결 
     - sts에서 maven 설정을 하니 maven 오류가 나지 않음 
     - [해결](https://aristatait.tistory.com/65) 사이트

   pom.xml에서 default 설정 완료 --2020.01.10

   - `<java-version>`  1.8로 변경
   - `<org.springframework-version>` 4.3.3.RELEASE로 변경
   - `<plugin>` 중 `<source>`, `<target>`1.8로 변경
   - [설치2](https://all-record.tistory.com/156) 사이트 참고

   spring과 톰캣 연동 완료 -- 2020.01.10

   - [설치1](https://all-record.tistory.com/46) [설치2](https://all-record.tistory.com/156) 순서대로 설치

    코드 실행 시 포트 중복 에러 해결 완료 -- 2020.01.10

   - 상황
     - HomeController.java를 tomcat서버를 통해 브라우저에 home.jsp를 올림
   - 문제
     - 다음과 같은 오류 발생

   ```word
   starting tomcat v8.5 server at localhost has encountered a problem
   ```

   - 해결 

     - 오류를 좀더 자세하게 보면 8080 , 8009포트를 oracle에서 사용하고 있다고 한다 그래서 수동으로 tomcat 포트 번호를 바꿔주면 해결 가능하다 ![](C:\Users\wjsxo\Downloads\clone_trello\ReadmeIMG1.png)

     - servers에서 Tomcat 을 실행해준다

       ![](C:\Users\wjsxo\Downloads\clone_trello\ReadmeIMG2.png)

     - 다음과 같은 페이지가 나오는데 HTTP/1.1이 8080 , AJP가 8009으로 되있다면 윗 사진과 같이 변경한다.

     - [해결](https://coding-factory.tistory.com/13)  사이트

   코드 실행 시 한글 깨짐 현상이 발생 -- 2020.01.10

   - 상황
     - 코드 실행
   - 문제
     - 브라우저에서 한글 깨짐
   - 해결
     - home.jsp파일에 다음 코드를 추가

   ```jsp
   <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <!-- UTF-8인코딩을 하기 위한 코드 -->
   <!--상단에 추가해 준다. -->
   ```