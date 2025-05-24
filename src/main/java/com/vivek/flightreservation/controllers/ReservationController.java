package com.vivek.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivek.flightreservation.constants.ApplicationConstants;
import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.services.ReservationService;

import jakarta.validation.Valid;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	ReservationService reservationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@GetMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation() invoked with FlightId : {}", flightId);

		Optional<Flight> flightOptional = flightRepository.findById(flightId);
		if (!flightOptional.isPresent()) {
			modelMap.addAttribute("error", "Flight not found");
			return "error";
		}

		modelMap.addAttribute("flight", flightOptional.get());
		modelMap.addAttribute("reservationRequest", new ReservationRequest());
		LOGGER.info("Flight is : {}", flightOptional.get());

		return ApplicationConstants.COMPLETE_RESERVATION_VIEW;
	}

	@PostMapping("/completeReservation")
	public String completeReservation(@Valid ReservationRequest request, BindingResult result, ModelMap modelMap) {
		LOGGER.info("Inside completeReservation() : {}", request);

		if (result.hasErrors()) {
			LOGGER.error("Validation errors: {}", result.getAllErrors());
			for (FieldError error : result.getFieldErrors()) {
				modelMap.addAttribute(error.getField() + "Error", error.getDefaultMessage());
			}
			// Get flight details again for the form
			Optional<Flight> flightOptional = flightRepository.findById(request.getFlightId());
			if (flightOptional.isPresent()) {
				modelMap.addAttribute("flight", flightOptional.get());
			}
			return ApplicationConstants.COMPLETE_RESERVATION_VIEW;
		}

		try {
			Reservation reservation = reservationService.bookFlight(request);
			modelMap.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
			return ApplicationConstants.RESERVATION_CONFIRMATION_VIEW;
		} catch (Exception e) {
			LOGGER.error("Error creating reservation: {}", e.getMessage());
			modelMap.addAttribute("error", "Error creating reservation: " + e.getMessage());
			// Get flight details again for the form
			Optional<Flight> flightOptional = flightRepository.findById(request.getFlightId());
			if (flightOptional.isPresent()) {
				modelMap.addAttribute("flight", flightOptional.get());
			}
			return ApplicationConstants.COMPLETE_RESERVATION_VIEW;
		}
	}
}
