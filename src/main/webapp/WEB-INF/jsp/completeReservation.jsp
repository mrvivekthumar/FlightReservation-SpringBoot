<%@ page import="com.vivek.flightreservation.entities.Flight" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Complete Reservation</title>
</head>
<body>
<h2>Complete Reservation</h2>

<%
    // Retrieve the flight attribute from the request scope
    Optional<Flight> flightOptional = (Optional<Flight>) request.getAttribute("flight");

    if (flightOptional.isPresent()) {
        Flight flight = flightOptional.get();
%>
Airline : <%= flight.getOperatingAirlines() %><br/>
Departure City  : <%= flight.getDepartureCity() %><br/>
Arrival City : <%= flight.getArrivalCity() %><br/>

<form action="completeReservation" method="post">
            <pre>
            <h2>Passenger Details : </h2>
            First Name : <input type="text" name="passengerFirstName"/>
            Last Name : <input type="text" name="passengerLastName"/>
            Email : <input type="text" name="passengerEmail"/>
            Phone: <input type="text" name="passengerPhone"/>

            <h2>Card Details : </h2>

            Name on the card : <input type="text" name="nameOnTheCard"/>
            Card No: <input type="text" name="cardNumber"/>
            Expiry Date : <input type="text" name="expirationDate"/>
            Three Digit Sec Code: <input type="text" name="securityCode"/>

            <input type="hidden" name="flightId" value="<%= flight.getId() %>" />
            <input type="submit" value="confirm" />
            </pre>
</form>
<%
} else {
%>
<p>No flight details available.</p>
<%
    }
%>

</body>
</html>
