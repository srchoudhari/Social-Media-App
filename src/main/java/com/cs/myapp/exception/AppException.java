package com.cs.myapp.exception;

public class AppException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AppException(final String msg) {
		super(msg);
	}
	
	public AppException(final String msg, Throwable e) {
		super(msg, e);
	}

}
