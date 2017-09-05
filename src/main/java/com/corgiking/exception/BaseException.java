package com.corgiking.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7540676135777494294L;

	private HttpStatus status;

	private ErrorCode errorCode;

	private String message;

	public BaseException(HttpStatus status, ErrorCode errorCode, String message) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}

	public BaseException(ErrorCode errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
