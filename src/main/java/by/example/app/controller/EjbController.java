package by.example.app.controller;


import by.example.app.eJavaBean.PersonBeanLocal;
import by.example.app.entity.Person;
import by.example.app.entity.PersonContext;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("EjbController")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class EjbController {

	@Inject
	private Logger logger;

	@EJB
	private PersonBeanLocal personBean;

	public EjbController() {
	}

	@PostConstruct
	private void postConstruct() {

		logger.info("@postConstruct method");
	}

	@GET
	@Path("all")
	public Response getPersons() throws Exception {
		try {

			logger.info("Initiated getIt method.");

			List<Person> peoples = personBean.getAll();

			return Response.ok(peoples).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("/{id}")
	public Response getejb(@PathParam("id") Long id) throws Exception {
		try {

			logger.info("Initiated getIt method.");

			Person person = personBean.get(id);

			return Response.ok(person).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}
	}

	@POST
	public Response insertPerson(final Person person) throws Exception {

		try {

			logger.info("Initiated invokeSessionBeanMethods method.");

			personBean.add(person);

			return Response.ok(person).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}

	}

	public static void main(String[] args) {

	}
}
