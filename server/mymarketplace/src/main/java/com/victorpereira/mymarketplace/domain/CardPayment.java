package com.victorpereira.mymarketplace.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.victorpereira.mymarketplace.domain.enums.PaymentState;

@Entity
@Table(name = "tb_card_payment")
public class CardPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer installmentsNumber;
	
	public CardPayment() {}

	public CardPayment(Integer id, PaymentState state, Order order, Integer installmentNumber) {
		super(id, state, order);
		this.installmentsNumber = installmentNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}
}
