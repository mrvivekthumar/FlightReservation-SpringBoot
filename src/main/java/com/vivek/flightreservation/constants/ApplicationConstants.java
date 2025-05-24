package com.vivek.flightreservation.constants;

public final class ApplicationConstants {
    
    private ApplicationConstants() {
        // Private constructor to prevent instantiation
    }

    // View Names
    public static final String LOGIN_VIEW = "login";
    public static final String REGISTER_VIEW = "register";
    public static final String FIND_FLIGHTS_VIEW = "findFlights";
    public static final String VIEW_FLIGHTS_VIEW = "viewFlights";
    public static final String COMPLETE_RESERVATION_VIEW = "completeReservation";
    public static final String RESERVATION_CONFIRMATION_VIEW = "reservationConfirmation";
    public static final String ERROR_VIEW = "error";

    // Session Attributes
    public static final String USER_ATTRIBUTE = "user";
    public static final String FLIGHT_ATTRIBUTE = "flight";
    public static final String RESERVATION_ATTRIBUTE = "reservation";

    // Error Messages
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String FLIGHT_NOT_FOUND = "Flight not found";
    public static final String RESERVATION_NOT_FOUND = "Reservation not found";
    public static final String VALIDATION_ERROR = "Validation error occurred";

    // Success Messages
    public static final String REGISTRATION_SUCCESS = "Registration successful. Please login.";
    public static final String RESERVATION_SUCCESS = "Reservation completed successfully.";
    public static final String LOGOUT_SUCCESS = "You have been logged out successfully.";
} 