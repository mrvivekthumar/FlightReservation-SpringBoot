package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.User;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/registerUser")
    public String showRegistrationPage() {
        return "registerUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "login";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        return "login";
    }
    
    @RequestMapping(value = "/findFlights", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        ModelMap modelMap) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return "findFlights";
        } else {
            modelMap.addAttribute("msg", "username or password is incorrect, please try again later");
        }


        return "showLogin";
    }
}
