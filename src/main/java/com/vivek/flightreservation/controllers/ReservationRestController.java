package com.vivek.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.flightreservation.dto.ReservationUpdateRequest;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@GetMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {

		LOGGER.info("Inside findReservation() for id : " + id);
		return reservationRepository.findById(id).orElse(null);
	}

	@PostMapping("/reservations")
	public Reservation updatReservation(@RequestBody ReservationUpdateRequest request) {

		LOGGER.info("Inside updatReservation()" + request);
		Reservation reservation = reservationRepository.findById(request.getId()).orElse(null);
		if (reservation != null) {
			LOGGER.info("Saving Reservation ");
			reservation.setNumberOfBags(request.getNumOfBags());
			reservation.setCheckedIn(request.getCheckedIn());
			return reservationRepository.save(reservation);
		}
		return null;
	}
}