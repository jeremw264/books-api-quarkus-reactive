package com.jeremw.bookstore.exception;

import lombok.Getter;
import jakarta.ws.rs.core.Response.Status;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Getter
public class ResourceException extends RuntimeException{

	private final String errorCode;
	private final Status status;

	public ResourceException(String errorCode,String errorMessage, Status status){
		super(errorMessage);
		this.errorCode = errorCode;
		this.status = status;
	}
}