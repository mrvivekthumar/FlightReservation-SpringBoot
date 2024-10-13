package com.vivek.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
