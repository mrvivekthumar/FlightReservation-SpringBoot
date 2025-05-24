<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>Add New Flight</title>
            <meta name="viewport" content="width=device-width, initial-scale=1">

            <!-- Bootstrap 5 CDN -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body class="bg-light">

            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="card shadow-sm">
                            <div class="card-header bg-primary text-white">
                                <h4 class="mb-0">Add New Flight</h4>
                            </div>
                            <div class="card-body">
                                <form:form action="addFlight" method="post" modelAttribute="flight"
                                    class="needs-validation" novalidate>
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                            <label for="flightNumber" class="form-label">Flight Number</label>
                                            <form:input path="flightNumber" class="form-control" required="required" />
                                            <form:errors path="flightNumber" cssClass="text-danger" />
                                        </div>
                                        <div class="col-md-6">
                                            <label for="operatingAirlines" class="form-label">Operating Airlines</label>
                                            <form:input path="operatingAirlines" class="form-control"
                                                required="required" />
                                            <form:errors path="operatingAirlines" cssClass="text-danger" />
                                        </div>
                                        <div class="col-md-6">
                                            <label for="departureCity" class="form-label">Departure City</label>
                                            <form:input path="departureCity" class="form-control" required="required" />
                                            <form:errors path="departureCity" cssClass="text-danger" />
                                        </div>
                                        <div class="col-md-6">
                                            <label for="arrivalCity" class="form-label">Arrival City</label>
                                            <form:input path="arrivalCity" class="form-control" required="required" />
                                            <form:errors path="arrivalCity" cssClass="text-danger" />
                                        </div>
                                        <div class="col-md-6">
                                            <label for="dateOfDeparture" class="form-label">Date of Departure</label>
                                            <form:input path="dateOfDeparture" type="date" class="form-control"
                                                required="required" />
                                            <form:errors path="dateOfDeparture" cssClass="text-danger" />
                                        </div>
                                        <div class="col-md-6">
                                            <label for="estimatedDepartureTime" class="form-label">Estimated Departure
                                                Time</label>
                                            <form:input path="estimatedDepartureTime" type="time" class="form-control"
                                                required="required" />
                                            <form:errors path="estimatedDepartureTime" cssClass="text-danger" />
                                        </div>
                                    </div>

                                    <div class="mt-4 d-grid gap-2 d-md-flex justify-content-md-end">
                                        <button type="submit" class="btn btn-primary btn-lg">Add Flight</button>
                                        <a href="/flightreservation/findFlights"
                                            class="btn btn-outline-secondary btn-lg">Cancel</a>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bootstrap & Form Validation Script -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                (function () {
                    'use strict';
                    var forms = document.querySelectorAll('.needs-validation');
                    Array.prototype.slice.call(forms).forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                })();
            </script>

        </body>

        </html>