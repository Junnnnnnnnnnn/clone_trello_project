<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form action="./login"  method="GET">
	<input type="text" name="ID">
	<input type="text" name="PW">
	<input type="submit">
</form>
	<a href="./signUp">SignUp</a>
</body>
</html>
