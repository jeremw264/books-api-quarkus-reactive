package com.jeremw.bookstore.exception;

import jakarta.ws.rs.core.Response.Status;
import lombok.Builder;
import lombok.Data;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
@Data
@Builder
public class ResourceExceptionDto {

	private String errorCode;
	private String errorMessage;
	private Status status;

}