package com.vivek.flightreservation.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	@Value("${com.vivek.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;

	@Value("${com.vivek.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;

	@Autowired
	private JavaMailSender sender;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

	public void sendItinerary(String toAddress, String filePaht) {
		LOGGER.info("Inside sendItinerary()");

		MimeMessage message = sender.createMimeMessage();

		try {

			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinerary", new File(filePaht));
			sender.send(message);

		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary" + e);
			e.printStackTrace();
		}

	}

}
