package com.jeremw.bookstore.exception;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * @author Jérémy Woirhaye
 * @version 1.0
 * @since 11/05/2024
 */
public class ExceptionMappers {
	@ServerExceptionMapper
	public RestResponse<ResourceExceptionDto> mapException(Exception exception) {

		ResourceExceptionDto resourceExceptionDto = ResourceExceptionDto.builder()
				.errorCode(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.errorMessage(exception.getMessage())
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.build();

		return RestResponse.status(resourceExceptionDto.getStatus(), resourceExceptionDto);

	}

	@ServerExceptionMapper
	public RestResponse<ResourceExceptionDto> mapResourceException(ResourceException exception) {

		ResourceExceptionDto resourceExceptionDto = ResourceExceptionDto.builder()
				.errorCode(exception.getErrorCode())
				.errorMessage(exception.getMessage())
				.status(exception.getStatus())
				.build();

		return RestResponse.status(resourceExceptionDto.getStatus(), resourceExceptionDto);

	}
}
