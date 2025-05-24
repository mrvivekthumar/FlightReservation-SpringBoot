<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8" />
        <title>Find Flights</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
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
                background: linear-gradient(to right, #74ebd5, #ACB6E5);
                padding: 20px;
            }

            .card {
                width: 100%;
                max-width: 700px;
                border-radius: 15px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .card-header {
                background-color: #007bff;
                color: white;
                padding: 1.2rem;
                border-radius: 15px 15px 0 0;
            }

            .card-body {
                background-color: white;
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
                padding: 12px;
                font-weight: 600;
            }

            .input-group:focus-within {
                box-shadow: 0 0 0 0.25rem rgba(33, 150, 243, 0.25);
                border-radius: 0.375rem;
            }
        </style>
    </head>

    <body>

        <div class="card">
            <div class="card-header">
                <h2 class="mb-0">
                    <i class="fas fa-search me-2"></i>Find Flights
                </h2>
            </div>
            <div class="card-body">
                <form action="findFlights" method="post">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="from" class="form-label">From</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fas fa-plane-departure"></i></span>
                                <input type="text" name="from" id="from" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="to" class="form-label">To</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fas fa-plane-arrival"></i></span>
                                <input type="text" name="to" id="to" class="form-control" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="departureDate" class="form-label">Departure Date</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
                                <input type="date" name="departureDate" id="departureDate" class="form-control"
                                    required>
                            </div>
                        </div>
                        <div class="col-12 text-center mt-4">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-search me-2"></i>Search Flights
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>