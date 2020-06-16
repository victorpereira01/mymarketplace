package com.victorpereira.mymarketplace.resources;

import java.util.ArrayList;
import java.util.List;

import com.victorpereira.mymarketplace.resources.exceptions.FieldMessage;
import com.victorpereira.mymarketplace.resources.exceptions.StandardError;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messages) {
		errors.add(new FieldMessage(fieldName, messages));
	}
}
