<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flights :</title>
</head>

<h2>Find Flights</h2>
<body>
	<form action="findFlights" method="post">
		<pre>
From : <input type="text" name="from" />

To: <input type="text" name="to" />

Departure Date: <input type="text" name="departureDate" />

<input type="submit" value="search" />

</pre>
	</form>

</body>
</html>