package com.example.untitled.infrastructure.filters;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Filters are mainly used to modify or process incoming and outgoing request or response headers.
 * It is important to realize that filters do not create the response—they only modify or adapt the requests and responses.
 * ClientRequestFilter and ClientResponseFilter are clientside filters
 * ContainerRequestFilter and ContainerResponseFilter are serverside filters
 * A filter and the target servlet always execute in the same invocation thread.
 */
@Provider
@PreMatching //will execute before the JAX-RS resource method is matched
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject
	private Logger logger;

	public CorsFilter() {

	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		logger.debug("ContainerRequestFilter was configured");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

		logger.debug("ContainerResponseFilter was configured");

		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		responseContext.getHeaders().add("yeah", ":)");
	}


}