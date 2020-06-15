package com.victorpereira.mymarketplace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.victorpereira.mymarketplace.domain.enums.PaymentState;

@Entity
@Table(name = "tb_billed_payment")
public class BilletPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date paymentDate;
	
	public BilletPayment() {}

	public BilletPayment(Integer id, PaymentState state, Order order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
