package com.vivek.flightreservation.controllers;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.services.ReservationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        modelMap.addAttribute("flight", flight);
        return "completeReservation";
    }

    @PostMapping(value ="/completeReservation")
    public String completeReservation(ReservationRequest request,ModelMap modelMap) {
    	Reservation reservation = reservationService.bookFlight(request);
    	modelMap.addAttribute("msg", "Reservation created successfully and the id "+ reservation.getId());
    	return "reservationConfirmation";
    }


}
