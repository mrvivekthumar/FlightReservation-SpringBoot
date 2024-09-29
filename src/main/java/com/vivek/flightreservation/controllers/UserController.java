package com.vivek.flightreservation.controllers;

import com.itextpdf.text.pdf.qrcode.Encoder;
import com.vivek.flightreservation.entities.User;
import com.vivek.flightreservation.repos.UserRepository;

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

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/register")
	public String registerForm() {
		LOGGER.info("Inside registerForm()");
		return "registerUser";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user) {
		LOGGER.info("Inside registerUser() : " + user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		LOGGER.info("Inside loginUser() and Email is :" + email);
		User user = userRepository.findByEmail(email);

		if (user != null && user.getPassword().equals(password)) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid username or password. Please try again.");
		}
		return "login";
	}
}
