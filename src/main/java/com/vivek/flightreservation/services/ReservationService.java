package com.vivek.flightreservation.services;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);

}
