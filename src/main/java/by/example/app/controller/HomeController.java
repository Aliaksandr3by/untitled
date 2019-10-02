package by.example.app.controller;


import by.example.app.eJavaBean.PersonBean;
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


@Path("/HomeController/")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class HomeController {

	private Logger logger;
	private PersonContext personContext;

	//@EJB
	private PersonBean userBean;

	public HomeController() {
	}

	@Inject
	public HomeController(Logger logger, PersonContext personContext) {
		this.logger = logger;
		this.personContext = personContext;
	}

	@PostConstruct
	private void postConstruct() {

		logger.info("HomeController @postConstruct method");
	}

	@GET
	@Path("")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getPersons() throws Exception {
		try {

			logger.info("Initiated getIt method.");

			List<Person> peoples = personContext.findAll("id");

			return Response.ok(peoples).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("{id}")
	public Response getPersonById(@PathParam("id") Long id) {
		try {

			logger.info("Initiated getIt method.");

			Person people = personContext.findById(new Person(id));

			return Response.ok(people).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("ejb")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getejb() throws Exception {
		try {

			Person person = userBean.get(1);

			return Response.ok(person).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@POST
	public Response insertPerson(final Person person) throws Exception {

		try {

			logger.info("Initiated invokeSessionBeanMethods method.");



			Person people = personContext.insert(person);

			Person tmp = personContext.findById(people);

			return Response.ok(tmp).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}

	}

	public static void main(String[] args) {

	}
}
