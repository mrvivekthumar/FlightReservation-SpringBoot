<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.vivek.flightreservation.entities.Flight" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Complete Reservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <style>
        body {
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px 15px;
        }

        .card {
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 900px;
        }

        .card-header {
            background-color: #007bff;
            color: white;
            border-radius: 15px 15px 0 0;
            padding: 1.2rem;
        }

        .card-body {
            background-color: #fff;
            border-radius: 0 0 15px 15px;
            padding: 1.5rem;
        }

        .input-group-text {
            background-color: transparent;
            border-right: none;
        }

        .form-control {
            border-left: none;
        }

        .form-control:focus {
            box-shadow: none;
            border-color: #ced4da;
        }

        .btn-primary {
            padding: 12px 30px;
            font-weight: 600;
        }

        .input-group:focus-within {
            box-shadow: 0 0 0 2px rgba(0, 123, 255, .25);
        }
    </style>
</head>

<body>
<%
    Flight flight = (Flight) request.getAttribute("flight");
    String error = (String) request.getAttribute("error");
%>

<div class="card">
    <div class="card-header">
        <h2 class="mb-0"><i class="fas fa-ticket-alt me-2"></i>Complete Reservation</h2>
    </div>
    <div class="card-body">
        <% if (error != null) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <%= error %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>

        <% if (flight != null) { %>
        <div class="mb-4">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title"><i class="fas fa-plane me-2"></i>Flight Details</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <p><strong>From:</strong> <%= flight.getDepartureCity() %></p>
                            <p><strong>To:</strong> <%= flight.getArrivalCity() %></p>
                        </div>
                        <div class="col-md-6">
                            <p><strong>Date:</strong> <%= flight.getDateOfDeparture() %></p>
                            <p><strong>Time:</strong> <%= flight.getEstimatedDepartureTime() %></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form action="completeReservation" method="post">
            <input type="hidden" name="flightId" value="<%= flight.getId() %>">
            <h5 class="mb-3"><i class="fas fa-user me-2"></i>Passenger Details</h5>
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">First Name</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" name="passengerFirstName" class="form-control" required
                               value="<%= request.getParameter("passengerFirstName") != null ? request.getParameter("passengerFirstName") : "" %>">
                    </div>
                    <% if (request.getAttribute("passengerFirstNameError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("passengerFirstNameError") %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Last Name</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" name="passengerLastName" class="form-control" required
                               value="<%= request.getParameter("passengerLastName") != null ? request.getParameter("passengerLastName") : "" %>">
                    </div>
                    <% if (request.getAttribute("passengerLastNameError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("passengerLastNameError") %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                        <input type="email" name="passengerEmail" class="form-control" required
                               value="<%= request.getParameter("passengerEmail") != null ? request.getParameter("passengerEmail") : "" %>">
                    </div>
                    <% if (request.getAttribute("passengerEmailError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("passengerEmailError") %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Phone</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-phone"></i></span>
                        <input type="tel" name="passengerPhone" class="form-control" required
                               value="<%= request.getParameter("passengerPhone") != null ? request.getParameter("passengerPhone") : "" %>">
                    </div>
                    <% if (request.getAttribute("passengerPhoneError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("passengerPhoneError") %></div>
                    <% } %>
                </div>
            </div>

            <h5 class="mb-3 mt-4"><i class="fas fa-credit-card me-2"></i>Card Details</h5>
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Name on Card</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" name="nameOnTheCard" class="form-control" required
                               value="<%= request.getParameter("nameOnTheCard") != null ? request.getParameter("nameOnTheCard") : "" %>">
                    </div>
                    <% if (request.getAttribute("nameOnTheCardError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("nameOnTheCardError") %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Card Number</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-credit-card"></i></span>
                        <input type="text" name="cardNumber" class="form-control" required
                               value="<%= request.getParameter("cardNumber") != null ? request.getParameter("cardNumber") : "" %>">
                    </div>
                    <% if (request.getAttribute("cardNumberError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("cardNumberError") %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Expiry Date</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                        <input type="text" name="expirationDate" class="form-control" required
                               placeholder="MM/YY"
                               value="<%= request.getParameter("expirationDate") != null ? request.getParameter("expirationDate") : "" %>">
                    </div>
                    <% if (request.getAttribute("expirationDateError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("expirationDateError") %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Security Code</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <input type="text" name="securityCode" class="form-control" required
                               value="<%= request.getParameter("securityCode") != null ? request.getParameter("securityCode") : "" %>">
                    </div>
                    <% if (request.getAttribute("securityCodeError") != null) { %>
                    <div class="text-danger mt-1"><%= request.getAttribute("securityCodeError") %></div>
                    <% } %>
                </div>
            </div>

            <div class="text-center mt-4">
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-check me-2"></i>Confirm Booking
                </button>
            </div>
        </form>
        <% } else { %>
        <div class="alert alert-warning">
            <i class="fas fa-exclamation-triangle me-2"></i>No flight details available.
        </div>
        <% } %>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
