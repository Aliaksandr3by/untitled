package com.example.untitled.infrastructure.filters;

import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

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