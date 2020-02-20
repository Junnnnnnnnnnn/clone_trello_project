<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>Trello Sign Up Page</h1>
	<form action = "./signUpResult" method="GET">
		Enter the id<input type="text" name="id"><br>
		Enter the password<input type="text" name="PW"><br>
		<input type="submit">
	</form>
</body>
</html>
