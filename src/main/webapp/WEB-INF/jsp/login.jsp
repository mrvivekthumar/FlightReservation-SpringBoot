<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
</head>
<body>
	<h2>Login :</h2>

	<form action="findFlights" method="post">
		<pre>
Email : <input type="text" name="email" />

Password : <input type="password" name="password" />

<input type="submit" value="login" />

</pre>

	</form>

	${msg}

</body>
</html>