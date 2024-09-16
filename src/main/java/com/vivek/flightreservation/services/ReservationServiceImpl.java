package com.vivek.flightreservation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.Passenger;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.repos.PassengerRepository;
import com.vivek.flightreservation.repos.ReservationRepository;
import com.vivek.flightreservation.util.EmailUtil;
import com.vivek.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	@Override
	public Reservation bookFlight(ReservationRequest request) {

//		Make Payment intergration Logic

		Long flightId = request.getFlightId();
		Optional<Flight> flight = flightRepository.findById(flightId);

		if (!flight.isPresent()) {
			throw new RuntimeException("Flight not found with id: " + flightId);
		}

		Flight flight1 = flight.get();

		Passenger passenger = new Passenger();
		passenger.setFirstname(request.getPassengerFirstName());
		passenger.setLastname(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerEmail());

		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight1);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = "/Users/thuma/Music/Reservation/reservation" + savedReservation.getId() + ".pdf";

		pdfGenerator.generateItinerary(savedReservation, filePath);

		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
