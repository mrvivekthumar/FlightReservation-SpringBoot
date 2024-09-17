package com.vivek.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vivek.flightreservation.dto.ReservationUpdateRequest;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@RequestMapping("/reservations/{id}")
	public Optional<Reservation> findReservation(@PathVariable("id") Long id) {

		LOGGER.info("Inside findReservation() for id : " + id);
		return reservationRepository.findById(id);
	}

	@RequestMapping("/reservations")
	public Reservation updatReservation(@RequestBody ReservationUpdateRequest request) {

		LOGGER.info("Inside updatReservation()" + request);
		Optional<Reservation> reservation = reservationRepository.findById(request.getId());
		reservation.get().setNumberOfBags(request.getNumOfBags());
		reservation.get().setCheckedIn(request.getCheckedIn());

		LOGGER.info("Saving Reservation ");
		return reservationRepository.save(reservation.get());
	}
}