package com.vivek.flightreservation.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.repos.FlightRepository;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository flightRepository;

    private Flight flight1;
    private Flight flight2;

    @BeforeEach
    void setUp() {
        flight1 = new Flight();
        flight1.setId(1L);
        flight1.setFlightNumber("FL123");
        flight1.setOperatingAirlines("Test Airlines 1");
        flight1.setDepartureCity("New York");
        flight1.setArrivalCity("London");
        flight1.setDateOfDeparture("2024-03-20");
        flight1.setEstimatedDepartureTime("10:00 AM");

        flight2 = new Flight();
        flight2.setId(2L);
        flight2.setFlightNumber("FL456");
        flight2.setOperatingAirlines("Test Airlines 2");
        flight2.setDepartureCity("London");
        flight2.setArrivalCity("Paris");
        flight2.setDateOfDeparture("2024-03-20");
        flight2.setEstimatedDepartureTime("02:00 PM");
    }

    @Test
    void findFlights_WithValidParameters_ShouldReturnViewFlightsView() throws Exception {
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findFlights(anyString(), anyString(), anyString())).thenReturn(flights);

        mockMvc.perform(post("/viewFlights")
                .param("from", "New York")
                .param("to", "London")
                .param("departureDate", "2024-03-20"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewFlights"))
                .andExpect(model().attribute("flights", flights));
    }

    @Test
    void findFlights_WithNoResults_ShouldReturnViewFlightsViewWithEmptyList() throws Exception {
        when(flightRepository.findFlights(anyString(), anyString(), anyString())).thenReturn(Arrays.asList());

        mockMvc.perform(post("/viewFlights")
                .param("from", "New York")
                .param("to", "Tokyo")
                .param("departureDate", "2024-03-20"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewFlights"))
                .andExpect(model().attribute("flights", Arrays.asList()));
    }

    @Test
    void showAddFlight_ShouldReturnAddFlightView() throws Exception {
        mockMvc.perform(get("/admin/showAddFlight"))
                .andExpect(status().isOk())
                .andExpect(view().name("addFlight"));
    }
} 