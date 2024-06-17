package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.repos.FlightRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        modelMap.addAttribute("flight", flight);
        return "completeReservation";
    }
    
    
    @RequestMapping(value ="/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request) {
    	return null;
    }
    
    
}
