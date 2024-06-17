package com.vivek.flightreservation.repos;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.vivek.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("SELECT f FROM Flight f WHERE f.departureCity = :departureCity AND f.arrivalCity = :arrivalCity AND f.dateOfDeparture = :dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to,
			@Param("dateOfDeparture") Date departureDate);

}
