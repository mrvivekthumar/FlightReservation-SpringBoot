package com.vivek.flightreservation.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vivek.flightreservation.dto.ReservationRequest;
import com.vivek.flightreservation.entities.Flight;
import com.vivek.flightreservation.entities.Passenger;
import com.vivek.flightreservation.entities.Reservation;
import com.vivek.flightreservation.repos.FlightRepository;
import com.vivek.flightreservation.repos.PassengerRepository;
import com.vivek.flightreservation.repos.ReservationRepository;
import com.vivek.flightreservation.services.ReservationServiceImpl;
import com.vivek.flightreservation.util.EmailUtil;
import com.vivek.flightreservation.util.PDFGenerator;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private PDFGenerator pdfGenerator;

    @Mock
    private EmailUtil emailUtil;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Flight flight;
    private Passenger passenger;
    private Reservation reservation;
    private ReservationRequest request;

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

        passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstname("John");
        passenger.setLastname("Doe");
        passenger.setEmail("john.doe@example.com");
        passenger.setPhone("+1234567890");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);
        reservation.setCreated(flight.getDateOfDeparture());

        request = new ReservationRequest();
        request.setFlightId(1L);
        request.setPassengerFirstName("John");
        request.setPassengerLastName("Doe");
        request.setPassengerEmail("john.doe@example.com");
        request.setPassengerPhone("+1234567890");
        request.setNameOnTheCard("John Doe");
        request.setCardNumber("1234567890123456");
        request.setExpirationDate("12/25");
        request.setSecurityCode("123");
    }

    @Test
    void whenBookFlight_thenReservationShouldBeCreated() {
        // Arrange
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation result = reservationService.bookFlight(request);

        // Assert
        assertNotNull(result);
        assertEquals(flight, result.getFlight());
        assertEquals(passenger, result.getPassenger());
        assertFalse(result.getCheckedIn());
        assertEquals(flight.getDateOfDeparture(), result.getCreated());
        
        verify(flightRepository).findById(1L);
        verify(passengerRepository).save(any(Passenger.class));
        verify(reservationRepository).save(any(Reservation.class));
        verify(pdfGenerator).generateItinerary(any(Reservation.class), anyString());
        verify(emailUtil).sendItinerary(eq(passenger.getEmail()), anyString());
    }

    @Test
    void whenBookFlightWithInvalidFlightId_thenThrowException() {
        // Arrange
        when(flightRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        request.setFlightId(999L);
        assertThrows(RuntimeException.class, () -> {
            reservationService.bookFlight(request);
        });
    }
} 