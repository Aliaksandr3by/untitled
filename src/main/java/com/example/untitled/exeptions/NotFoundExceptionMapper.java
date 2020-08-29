package com.example.untitled.exeptions;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundExceptions> {

	@Override
	public Response toResponse(com.example.untitled.exeptions.NotFoundExceptions exception) {
		return Response.status(Response.Status.PRECONDITION_FAILED)
				.entity("Response not found")
				.build();
	}


}