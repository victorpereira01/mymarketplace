package com.victorpereira.mymarketplace.domain.enums;

public enum PaymentState {

	PENDING(1),
	PAID(2),
	CANCELED(3);

	private int code;

	private PaymentState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PaymentState toEnum(Integer code) {
		if (code == null)
			return null;

		for (PaymentState x : PaymentState.values()) {
			if (code.equals(x.getCode()))
				return x;
		}
		throw new IllegalArgumentException("Invalid Id: " + code);
	}
}
