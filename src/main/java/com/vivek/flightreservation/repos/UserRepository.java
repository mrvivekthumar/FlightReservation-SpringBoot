package com.vivek.flightreservation.repos;

import com.vivek.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
