package com.example.untitled.infrastructure.filters;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.ext.Provider;


@Deprecated
//This type of filters is supported only as part of the Client API.
@Provider
public class ClientFilter implements ClientRequestFilter, ClientResponseFilter {

	public ClientFilter() {
	}

	@Override
	public void filter(ClientRequestContext requestContext) {

	}

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {

	}
}