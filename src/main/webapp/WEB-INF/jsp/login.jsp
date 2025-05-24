<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Login</title>
		<!-- Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
		<!-- FontAwesome for icons -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
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
			}

			.card {
				border-radius: 15px;
				background: white;
				box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
				width: 100%;
				max-width: 400px;
			}

			.card-body {
				padding: 2.5rem;
			}

			.input-group-text {
				background-color: transparent;
				border-right: none;
				border-radius: 0.375rem 0 0 0.375rem;
			}

			.form-control {
				border-left: none;
				border-radius: 0 0.375rem 0.375rem 0;
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

			h2 {
				margin-bottom: 1.5rem;
			}

			.text-center a {
				text-decoration: none;
				font-weight: 600;
				color: #0d6efd;
			}

			.text-center a:hover {
				text-decoration: underline;
			}
		</style>
	</head>

	<body>
		<div class="card shadow">
			<div class="card-body">
				<h2 class="text-center">Welcome Back!</h2>
				<% if (request.getAttribute("msg") != null) { %>
					<div class="alert alert-<%= request.getAttribute("msg").toString().contains("success") ? "success" : "danger" %> alert-dismissible fade show" role="alert">
						<%= request.getAttribute("msg") %>
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				<% } %>
				<form action="login" method="post" autocomplete="on">
					<div class="mb-3">
						<label for="email" class="form-label">Email address</label>
						<div class="input-group">
							<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							<input type="email" class="form-control" id="email" name="email" required
								autocomplete="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" />
						</div>
					</div>
					<div class="mb-4">
						<label for="password" class="form-label">Password</label>
						<div class="input-group">
							<span class="input-group-text"><i class="fas fa-lock"></i></span>
							<input type="password" class="form-control" id="password" name="password" required
								autocomplete="current-password" />
						</div>
					</div>
					<div class="d-grid">
						<button type="submit" class="btn btn-primary btn-lg">Sign In</button>
					</div>
				</form>
				<div class="text-center mt-4">
					<p class="mb-0">Don't have an account? <a href="register">Register here</a></p>
				</div>
			</div>
		</div>

		<!-- Bootstrap JS Bundle (Popper included) -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	</body>

	</html>