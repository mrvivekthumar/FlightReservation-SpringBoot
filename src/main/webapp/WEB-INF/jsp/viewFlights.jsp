<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.List, com.vivek.flightreservation.entities.Flight" %>
<%
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Available Flights</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- FontAwesome -->
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <style>
        html,
        body {
            height: 100%;
            margin: 0;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(120deg, #89f7fe 0%, #66a6ff 100%);
            padding: 20px;
        }

        .card {
            width: 100%;
            max-width: 1000px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background-color: #007bff;
            color: white;
            border-radius: 15px 15px 0 0;
            padding: 1.2rem;
        }

        .card-body {
            padding: 1.5rem;
            background-color: white;
            border-radius: 0 0 15px 15px;
        }

        .table td,
        .table th {
            vertical-align: middle;
        }

        .btn-sm {
            padding: 6px 12px;
        }
    </style>
</head>

<body>
    <div class="card">
        <div class="card-header">
            <h2 class="mb-0">
                <i class="fas fa-plane me-2"></i>Available Flights
            </h2>
        </div>
        <div class="card-body">
            <% if (error != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <%= error %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <% } %>
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                        <tr>
                            <th><i class="fas fa-plane"></i> Airline</th>
                            <th><i class="fas fa-plane-departure"></i> Departure City</th>
                            <th><i class="fas fa-plane-arrival"></i> Arrival City</th>
                            <th><i class="fas fa-calendar"></i> Departure Date</th>
                            <th><i class="fas fa-clock"></i> Estimated Departure Time</th>
                            <th><i class="fas fa-ticket-alt"></i> Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (flights != null && !flights.isEmpty()) { 
                            for (Flight flight : flights) { %>
                            <tr>
                                <td><%= flight.getOperatingAirlines() %></td>
                                <td><%= flight.getDepartureCity() %></td>
                                <td><%= flight.getArrivalCity() %></td>
                                <td><%= flight.getDateOfDeparture() %></td>
                                <td><%= flight.getEstimatedDepartureTime() %></td>
                                <td>
                                    <a href="showCompleteReservation?flightId=<%= flight.getId() %>"
                                        class="btn btn-primary btn-sm">
                                        <i class="fas fa-check me-1"></i>Select
                                    </a>
                                </td>
                            </tr>
                        <% } 
                        } else { %>
                            <tr>
                                <td colspan="6" class="text-center text-muted">
                                    <% if (error == null) { %>
                                        No flights found matching your search criteria.
                                    <% } %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div class="mt-3">
                <a href="findFlights" class="btn btn-secondary">
                    <i class="fas fa-search me-1"></i>Search Again
                </a>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>