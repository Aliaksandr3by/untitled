package com.example.untitled.exeptions;

public class NotImplementedException extends RuntimeException {

	public NotImplementedException() {}

	public NotImplementedException(String ex) {
		super(ex);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
