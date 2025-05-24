<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.vivek.flightreservation.entities.Reservation" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Confirmation</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
        }

        .confirmation-container {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 800px;
            width: 100%;
        }

        .success-icon {
            color: #28a745;
            font-size: 4rem;
            margin-bottom: 1rem;
        }

        .error-icon {
            color: #dc3545;
            font-size: 4rem;
            margin-bottom: 1rem;
        }

        .confirmation-message {
            color: #333;
            font-size: 1.2rem;
            margin-bottom: 2rem;
        }

        .reservation-details {
            background: #f8f9fa;
            border-radius: 8px;
            padding: 1.5rem;
            margin-bottom: 2rem;
            text-align: left;
        }

        .reservation-details h3 {
            color: #007bff;
            margin-bottom: 1rem;
            font-size: 1.4rem;
        }

        .detail-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 0.5rem;
            padding: 0.5rem 0;
            border-bottom: 1px solid #dee2e6;
        }

        .detail-row:last-child {
            border-bottom: none;
        }

        .detail-label {
            font-weight: 600;
            color: #495057;
        }

        .detail-value {
            color: #212529;
        }

        .btn-home {
            background: #007bff;
            color: white;
            padding: 0.8rem 2rem;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .btn-home:hover {
            background: #0056b3;
            color: white;
        }

        .no-message {
            color: #6c757d;
            font-size: 1.1rem;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="confirmation-container">
            <% 
                String msg = (String) request.getAttribute("msg");
                String error = (String) request.getAttribute("error");
                Reservation reservation = (Reservation) request.getAttribute("reservation");
            %>
            
            <% if (error != null) { %>
                <i class="fas fa-exclamation-circle error-icon"></i>
                <div class="confirmation-message text-danger">
                    <%= error %>
                </div>
            <% } else if (msg != null) { %>
                <i class="fas fa-check-circle success-icon"></i>
                <div class="confirmation-message">
                    <%= msg %>
                </div>
                
                <% if (reservation != null) { %>
                    <div class="reservation-details">
                        <h3><i class="fas fa-ticket-alt me-2"></i>Reservation Details</h3>
                        <div class="detail-row">
                            <span class="detail-label">Reservation ID:</span>
                            <span class="detail-value"><%= reservation.getId() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Flight Number:</span>
                            <span class="detail-value"><%= reservation.getFlight().getFlightNumber() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Airline:</span>
                            <span class="detail-value"><%= reservation.getFlight().getOperatingAirlines() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">From:</span>
                            <span class="detail-value"><%= reservation.getFlight().getDepartureCity() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">To:</span>
                            <span class="detail-value"><%= reservation.getFlight().getArrivalCity() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Departure Date:</span>
                            <span class="detail-value"><%= reservation.getFlight().getDateOfDeparture() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Departure Time:</span>
                            <span class="detail-value"><%= reservation.getFlight().getEstimatedDepartureTime() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Passenger Name:</span>
                            <span class="detail-value"><%= reservation.getPassenger().getFirstname() %> <%= reservation.getPassenger().getLastname() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Email:</span>
                            <span class="detail-value"><%= reservation.getPassenger().getEmail() %></span>
                        </div>
                        <div class="detail-row">
                            <span class="detail-label">Phone:</span>
                            <span class="detail-value"><%= reservation.getPassenger().getPhone() %></span>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <i class="fas fa-exclamation-circle text-warning" style="font-size: 4rem;"></i>
                <p class="no-message">No confirmation message available.</p>
            <% } %>
            
            <a href="findFlights" class="btn btn-home">
                <i class="fas fa-home me-2"></i>Back to Home
            </a>
        </div>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>