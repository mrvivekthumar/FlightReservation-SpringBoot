<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Flight Reservation Application</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <style>
            html,
            body {
                height: 100%;
                margin: 0;
            }

            body {
                background: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
                /* Use Flexbox on the body to center content vertically and horizontally */
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
            }

            .container {
                /* Make container a flex container to center the welcome box */
                display: flex;
                align-items: center;
                justify-content: center;
                width: 100%;
            }

            .welcome-container {
                background: white;
                padding: 2rem;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                text-align: center;
                max-width: 600px;
                width: 100%;
            }

            .welcome-title {
                color: #333;
                margin-bottom: 2rem;
            }

            .btn-welcome {
                padding: 0.8rem 2rem;
                margin: 0.5rem;
                font-weight: bold;
                transition: all 0.3s ease;
            }

            .btn-register {
                background: #2196F3;
                color: white;
            }

            .btn-register:hover {
                background: #1976D2;
                color: white;
            }

            .btn-login {
                background: #4CAF50;
                color: white;
            }

            .btn-login:hover {
                background: #45a049;
                color: white;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="welcome-container">
                <h1 class="welcome-title">Welcome to Flight Reservation</h1>
                <p class="lead mb-4">Your one-stop solution for booking flights</p>
                <div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
                    <a href="register" class="btn btn-welcome btn-register">New User? Register</a>
                    <a href="login" class="btn btn-welcome btn-login">Existing User? Login</a>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>