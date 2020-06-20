package com.victorpereira.mymarketplace.services;

import org.springframework.mail.SimpleMailMessage;

import com.victorpereira.mymarketplace.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
}
