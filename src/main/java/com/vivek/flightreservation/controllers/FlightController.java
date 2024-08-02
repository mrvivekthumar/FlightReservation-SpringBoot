package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/findFlights")
    public String findFlightsForm() {
        return "findFlights";
    }

    @PostMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                              @RequestParam("departureDate") String departureDate,
                              ModelMap modelMap)
    {
        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
        modelMap.addAttribute("flights", flights);
        System.out.println("hii2");
        return "viewFlights";
    }

    @GetMapping("/viewFlights")
    public String viewFlightsForm() {
        return "viewFlights";
    }
}