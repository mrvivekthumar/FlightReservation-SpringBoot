package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                              @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
                              ModelMap modelMap) {

        try {
            List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
            modelMap.addAttribute("flights", flights);
            return "displayFlights";
        } catch (Exception e) {
            modelMap.addAttribute("errorMessage", "An error occurred while searching for flights.");
            e.printStackTrace();
            return "errorPage"; // Make sure to create an appropriate error page
        }
    }

}