<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register User</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <style>
            html,
            body {
                height: 100%;
                margin: 0;
            }

            body {
                background: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                /* full viewport height */
                /* remove padding so flex centering is perfect */
                padding: 0;
            }

            .register-container {
                background: white;
                padding: 2rem;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
            }

            .register-title {
                color: #333;
                text-align: center;
                margin-bottom: 1.5rem;
            }

            .form-control {
                border-radius: 5px;
                padding: 0.8rem;
                margin-bottom: 1rem;
            }

            .btn-register {
                background: #2196F3;
                color: white;
                padding: 0.8rem;
                border: none;
                border-radius: 5px;
                width: 100%;
                font-weight: bold;
                transition: background 0.3s ease;
            }

            .btn-register:hover {
                background: #1976D2;
            }

            .form-label {
                font-weight: 500;
                color: #555;
            }
        </style>
    </head>

    <body>
        <div class="register-container">
            <h2 class="register-title">Create Account</h2>
            <form action="register" method="post" autocomplete="on">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstname" class="form-label">First Name</label>
                        <input type="text" class="form-control" id="firstname" name="firstname" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastname" class="form-label">Last Name</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" required>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>
                <button type="submit" class="btn btn-register">Create Account</button>
            </form>
        </div>

        <!-- Bootstrap JS and Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </body>

    </html>