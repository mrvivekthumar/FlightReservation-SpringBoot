package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping(value = "/findFlights",method = RequestMethod.POST)
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                              @RequestParam("departureDate") @DateTimeFormat(pattern ="dd-MM-yyyy") Date departureDate,
                              ModelMap modelMap)
    {
        try {
            System.out.println("hii");
            List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
            modelMap.addAttribute("flights", flights);
            System.out.println("hii2");

            return "displayFlights";

        } catch (Exception e) {
            modelMap.addAttribute("errorMessage", "An error occurred while searching for flights.");
            e.printStackTrace();
            return "errorPage"; // Make sure to create an appropriate error page
        }
    }

}