package com.example.untitled.exeptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(Response.Status.PRECONDITION_FAILED)
				.entity("Response not found")
				.build();
	}
}