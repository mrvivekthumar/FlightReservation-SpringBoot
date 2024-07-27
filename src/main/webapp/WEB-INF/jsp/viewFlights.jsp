<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Flights</title>
</head>
<body>
  <h2>View Flights :</h2>
  <table>
    <tr>
      <th>Airlines</th>
      <th>Departure City</th>
      <th>Arrival City</th> 
      <th>Departure Time</th>
    </tr>
    <c:forEach var="flight" items="${flights}"> <tr>
        <td><c:out value="${flight.operatingAirlines}" /></td>  <td><c:out value="${flight.departureCity}" /></td>   <td><c:out value="${flight.arrivalCity}" /></td>     <td><c:out value="${flight.estimatedDepartureTime}" /></td> </tr>
    </c:forEach>
  </table>
</body>
</html>
