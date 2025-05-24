package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivek.flightreservation.entities.User;
import com.vivek.flightreservation.repos.UserRepository;
import com.vivek.flightreservation.constants.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/flightreservation")
	public String showHome() {
		return "index";
	}

	@GetMapping("/register")
	public String registerForm() {
		LOGGER.info("Inside registerForm()");
		return ApplicationConstants.REGISTER_VIEW;
	}

	@GetMapping("/login")
	public String loginForm() {
		LOGGER.info("Inside loginForm()");
		return ApplicationConstants.LOGIN_VIEW;
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, ModelMap modelMap) {
		LOGGER.info("Inside registerUser() : " + user);

		// Check if user already exists
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			modelMap.addAttribute("msg", "User with this email already exists. Please login instead.");
			return ApplicationConstants.LOGIN_VIEW;
		}

		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		modelMap.addAttribute("msg", ApplicationConstants.REGISTRATION_SUCCESS);
		return ApplicationConstants.LOGIN_VIEW;
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Inside login() and the email is: " + email);

		// Check if user exists
		User user = userRepository.findByEmail(email);
		if (user == null) {
			modelMap.addAttribute("msg", ApplicationConstants.USER_NOT_FOUND);
			return ApplicationConstants.LOGIN_VIEW;
		}

		boolean loginResponse = securityService.login(email, password, request, response);
		if (loginResponse) {
			return ApplicationConstants.FIND_FLIGHTS_VIEW;
		} else {
			modelMap.addAttribute("msg", ApplicationConstants.INVALID_CREDENTIALS);
		}
		return ApplicationConstants.FIND_FLIGHTS_VIEW;
	}
}
