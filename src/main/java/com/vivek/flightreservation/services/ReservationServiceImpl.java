package com.vivek.flightreservation.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${com.vivek.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	public Reservation bookFlight(ReservationRequest request) {

//		Make Payment intergration Logic

		LOGGER.info("Inside bookFlight ()");

		Long flightId = request.getFlightId();
		LOGGER.info("Fetching Flight for FlightId : " + flightId);
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

		LOGGER.info("Saving the passenger : " + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight1);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		reservation.setCreated(flight1.getDateOfDeparture());

		LOGGER.info("Saving the reservation : " + reservation);
		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";

		LOGGER.info("Generating the itinerary : ");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the itinerary : ");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

	@Override
	public String toString() {
		return "ReservationServiceImpl [flightRepository=" + flightRepository + ", passengerRepository="
				+ passengerRepository + ", reservationRepository=" + reservationRepository + ", pdfGenerator="
				+ pdfGenerator + ", emailUtil=" + emailUtil + "]";
	}

}
