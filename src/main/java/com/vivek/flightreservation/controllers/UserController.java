package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.entities.User;
import com.vivek.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/register")
    public String registerForm() {
        return "registerUser";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
    	userRepository.save(user);
        return "login";
    }
    
    @GetMapping("/login")
    public String loginForm() {
    	return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
      User user = userRepository.findByEmail(email);

      if (user != null && user.getPassword().equals(password)) {
        return "findFlights";
      } else {
        modelMap.addAttribute("msg", "Invalid username or password. Please try again.");
      }
      return "login";
    }
}
