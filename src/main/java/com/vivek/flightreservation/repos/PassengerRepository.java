package com.vivek.flightreservation.repos;

import com.vivek.flightreservation.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
