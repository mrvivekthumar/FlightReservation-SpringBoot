package com.vivek.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
