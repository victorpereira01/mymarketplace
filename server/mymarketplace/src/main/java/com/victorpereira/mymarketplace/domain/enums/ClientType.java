package com.victorpereira.mymarketplace.domain.enums;

public enum ClientType {
	
	PHYSICALPERSON(1),
	LEGALPERSON(2);
	
	private int code;
	
	private ClientType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ClientType toEnum(Integer code) {
		if(code == null)
			return null;
		
		for (ClientType x : ClientType.values()) {
			if(code.equals(x.getCode()))
				return x;
		}
		throw new IllegalArgumentException("Invalid Id: " + code);
	}
}
