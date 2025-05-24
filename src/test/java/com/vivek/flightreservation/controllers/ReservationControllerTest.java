package com.vivek.flightreservation.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.services.ReservationService;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private ReservationService reservationService;

    private Flight flight;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FL123");
        flight.setOperatingAirlines("Test Airlines");
        flight.setDepartureCity("New York");
        flight.setArrivalCity("London");
        flight.setDateOfDeparture("2024-03-20");
        flight.setEstimatedDepartureTime("10:00 AM");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setFlight(flight);
    }

    @Test
    void showCompleteReservation_WithValidFlightId_ShouldReturnCompleteReservationView() throws Exception {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        mockMvc.perform(get("/showCompleteReservation")
                .param("flightId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("completeReservation"))
                .andExpect(model().attributeExists("flight"))
                .andExpect(model().attributeExists("reservationRequest"));
    }

    @Test
    void showCompleteReservation_WithInvalidFlightId_ShouldReturnErrorView() throws Exception {
        when(flightRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/showCompleteReservation")
                .param("flightId", "999"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attribute("error", "Flight not found"));
    }

    @Test
    void completeReservation_WithValidRequest_ShouldReturnReservationConfirmationView() throws Exception {
        when(reservationService.bookFlight(any(ReservationRequest.class))).thenReturn(reservation);

        mockMvc.perform(post("/completeReservation")
                .param("flightId", "1")
                .param("passengerFirstName", "John")
                .param("passengerLastName", "Doe")
                .param("passengerEmail", "john.doe@example.com")
                .param("passengerPhone", "+1234567890")
                .param("nameOnTheCard", "John Doe")
                .param("cardNumber", "1234567890123456")
                .param("expirationDate", "12/25")
                .param("securityCode", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("reservationConfirmation"))
                .andExpect(model().attributeExists("msg"));
    }

    @Test
    void completeReservation_WithInvalidRequest_ShouldReturnCompleteReservationView() throws Exception {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));

        mockMvc.perform(post("/completeReservation")
                .param("flightId", "1")
                .param("passengerFirstName", "") // Invalid: empty first name
                .param("passengerLastName", "Doe")
                .param("passengerEmail", "invalid-email") // Invalid: wrong email format
                .param("passengerPhone", "123") // Invalid: wrong phone format
                .param("nameOnTheCard", "John Doe")
                .param("cardNumber", "1234") // Invalid: wrong card number format
                .param("expirationDate", "13/25") // Invalid: wrong date format
                .param("securityCode", "12")) // Invalid: wrong security code format
                .andExpect(status().isOk())
                .andExpect(view().name("completeReservation"))
                .andExpect(model().attributeExists("flight"))
                .andExpect(model().attributeExists("passengerFirstNameError"))
                .andExpect(model().attributeExists("passengerEmailError"))
                .andExpect(model().attributeExists("passengerPhoneError"))
                .andExpect(model().attributeExists("cardNumberError"))
                .andExpect(model().attributeExists("expirationDateError"))
                .andExpect(model().attributeExists("securityCodeError"));
    }
} 