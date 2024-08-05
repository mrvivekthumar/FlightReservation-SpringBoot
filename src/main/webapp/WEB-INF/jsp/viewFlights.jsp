<%@ page import="com.vivek.flightreservation.entities.Flight" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Flights</title>
</head>
<body>
<h2>Available Flights</h2>
<%
  List<Flight> flights = (List<Flight>) request.getAttribute("flights");

  if (flights != null && !flights.isEmpty()) {
%>
<table border="1">
  <thead>
  <tr>
    <th>Airline</th>
    <th>Departure City</th>
    <th>Arrival City</th>
    <th>Estimeted Departure Time</th>
  </tr>
  </thead>
  <tbody>
  <%
    // Iterate through the list of flights and display each one
    for (Flight flight : flights) {
  %>
  <tr>
    <td><%= flight.getOperatingAirlines() %></td>
    <td><%= flight.getDepartureCity() %></td>
    <td><%= flight.getArrivalCity() %></td>
    <td><%= flight.getEstimatedDepartureTime() %></td>
    <td><a href="showCompleteReservation?flightId=<%= flight.getId() %>">Select</a></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
<%
} else {
%>
<p>No flights found.</p>
<%
  }
%>
</body>
</html>
