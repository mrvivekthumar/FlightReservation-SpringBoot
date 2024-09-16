package com.vivek.flightreservation.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender sender;

	public void sendItinerary(String toAddress, String filePaht) {

		MimeMessage message = sender.createMimeMessage();

		try {

			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject("Itinerary for your flight");
			messageHelper.setText("please find your Itinerary attached");
			messageHelper.addAttachment("Itinerary", new File(filePaht));
			sender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
