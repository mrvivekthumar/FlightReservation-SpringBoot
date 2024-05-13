package com.vivek.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
