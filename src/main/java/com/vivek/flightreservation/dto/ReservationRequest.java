package com.vivek.flightreservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ReservationRequest {
	
	@NotNull(message = "Flight ID is required")
	private Long flightId;
	
	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	private String passengerFirstName;
	
	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	private String passengerLastName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	private String passengerEmail;
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Please provide a valid phone number")
	private String passengerPhone;

	@NotBlank(message = "Name on card is required")
	@Size(min = 3, max = 100, message = "Name on card must be between 3 and 100 characters")
	private String nameOnTheCard;
	
	@NotBlank(message = "Card number is required")
	@Pattern(regexp = "^\\d{16}$", message = "Card number must be 16 digits")
	private String cardNumber;
	
	@NotBlank(message = "Expiration date is required")
	@Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Expiration date must be in MM/YY format")
	private String expirationDate;
	
	@NotBlank(message = "Security code is required")
	@Pattern(regexp = "^\\d{3,4}$", message = "Security code must be 3 or 4 digits")
	private String securityCode;

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getPassengerFirstName() {
		return passengerFirstName;
	}

	public void setPassengerFirstName(String passengerFirstName) {
		this.passengerFirstName = passengerFirstName;
	}

	public String getPassengerLastName() {
		return passengerLastName;
	}

	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}

	public String getPassengerEmail() {
		return passengerEmail;
	}

	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}

	public String getPassengerPhone() {
		return passengerPhone;
	}

	public void setPassengerPhone(String passengerPhone) {
		this.passengerPhone = passengerPhone;
	}

	public String getNameOnTheCard() {
		return nameOnTheCard;
	}

	public void setNameOnTheCard(String nameOnTheCard) {
		this.nameOnTheCard = nameOnTheCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String toString() {
		return "ReservationRequest [flightId=" + flightId + ", passengerFirstName=" + passengerFirstName
				+ ", passengerLastName=" + passengerLastName + ", passengerEmail=" + passengerEmail
				+ ", passengerPhone=" + passengerPhone + ", nameOnTheCard=" + nameOnTheCard + ", cardNumber="
				+ cardNumber + ", expirationDate=" + expirationDate + ", securityCode=" + securityCode + "]";
	}

}
