package com.yy.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CustomException(HttpStatus status, ErrorCode errorCode, String message) {
		super(status, errorCode, message);
	}

	public CustomException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}
