package com.vivek.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.flightreservation.dto.ReservationUpdateRequest;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {

	@Autowired
	ReservationRepository reservationRepository;
	
	@RequestMapping("/reservations/{id}")
	public Optional<Reservation> findReservation(@PathVariable("id ") Long id) {
		return reservationRepository.findById(id);
	}
	
	@RequestMapping("/reservations")
	public Reservation updatReservation(@RequestBody ReservationUpdateRequest request) {
		
		Reservation reservation = reservationRepository.findOne(request.getId());
		reservation.setNumberOfBags(request.getNumOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		return reservationRepository.save(reservation);
	}
	
	
}