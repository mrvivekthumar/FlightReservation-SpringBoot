package com.vivek.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
