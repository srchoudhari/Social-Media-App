package com.cs.myapp.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorMsg {
	private HttpStatus status;
    private String message;
    private Date timestamp;
    private String description;

	public ErrorMsg(HttpStatus status, String message, Date timestamp, String description) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.description = description;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDescription() {
		return description;
	}

	
}
