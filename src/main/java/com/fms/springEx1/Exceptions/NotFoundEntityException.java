package com.fms.springEx1.Exceptions;

import lombok.Getter;

public class NotFoundEntityException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	private ErrorCode code;

	public NotFoundEntityException(String message) {
		super(message);
	}
	
	public NotFoundEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundEntityException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}
	
	public NotFoundEntityException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

}
