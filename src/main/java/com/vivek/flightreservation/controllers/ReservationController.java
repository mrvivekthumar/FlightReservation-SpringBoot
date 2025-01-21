package com.vivek.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@GetMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation() invoked with FlightId : ", flightId);

		Optional<Flight> flight = flightRepository.findById(flightId);
		modelMap.addAttribute("flight", flight);
		LOGGER.info("Flight is : ", flight);

		return "completeReservation";
	}

	@PostMapping("/completeReservation")
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {

		LOGGER.info("Inside completeReservation() is : " + request);

		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation created successfully and the id " + reservation.getId());
		return "reservationConfirmation";
	}

}
