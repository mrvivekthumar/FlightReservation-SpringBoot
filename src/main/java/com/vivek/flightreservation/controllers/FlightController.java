package com.vivek.flightreservation.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	@PostMapping("/viewFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") String departureDate, ModelMap modelMap) {
		LOGGER.info("Inside findFlights() From : " + from + " To : " + to + " Departure date : " + departureDate);
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		System.out.println("hii2");
		return "viewFlights";
	}

	@GetMapping("/admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}

}