package com.example.ems1.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	public void sendotp(String toEmail,String otp ) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Otp fir Verification");
		message.setText("ur Otp is " + otp + ".It will expire in 10 min!!!");
	    
		javaMailSender.send(message);
	}
}
