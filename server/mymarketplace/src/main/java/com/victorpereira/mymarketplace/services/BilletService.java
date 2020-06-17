package com.victorpereira.mymarketplace.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.victorpereira.mymarketplace.domain.BilletPayment;

@Service
public class BilletService {

	public void fillBilletPayment(BilletPayment pay, Date orderInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(cal.getTime());
	}
}
