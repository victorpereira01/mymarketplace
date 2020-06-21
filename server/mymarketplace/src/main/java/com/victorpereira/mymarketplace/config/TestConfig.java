package com.victorpereira.mymarketplace.config;

import java.io.InputStream;
import java.text.ParseException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.victorpereira.mymarketplace.services.DBService;
import com.victorpereira.mymarketplace.services.EmailService;
import com.victorpereira.mymarketplace.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
	@Bean
	public JavaMailSender javaMailSender() { 
        return new JavaMailSender() {
			
			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {
			}
			
			@Override
			public void send(SimpleMailMessage simpleMessage) throws MailException {
			}
			
			@Override
			public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
			}
			
			@Override
			public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
			}
			
			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {
			}
			
			@Override
			public void send(MimeMessage mimeMessage) throws MailException {
			}
			
			@Override
			public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
				return null;
			}
			
			@Override
			public MimeMessage createMimeMessage() {
				return null;
			}
		};
  } 
	
}
