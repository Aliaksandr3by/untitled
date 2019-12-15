package com.example.untitled.infrastructure.filters;

import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filters are mainly used to modify or process incoming and outgoing request or response headers.
 * ClientRequestFilter and ClientResponseFilter are clientside filters
 * ContainerRequestFilter and ContainerResponseFilter are serverside filters
 */
@Provider
@PreMatching //will execute before the JAX-RS resource method is matched
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject
	private transient Logger logger;

	public CorsFilter() {
		System.out.println("hkhkhk");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		logger.info(this.getClass().getName() + " was configured " + requestContext.getMethod());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

		logger.info(this.getClass().getName() + " was configured " + requestContext.getMethod());

		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}


}