package by.example.app.presentation;


import by.example.app.domain.Person;
import by.example.app.models.PersonContext;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("HomeController")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class PersonController {

	private transient Logger logger;
	private PersonContext personContext;

	public PersonController() {
	}

	@Inject
	public PersonController(final Logger logger, final PersonContext personContext) {
		this.logger = logger;
		this.personContext = personContext;
	}

	@PostConstruct
	private void postConstruct() {

		logger.info("HomeController @postConstruct info", this.getClass().getSimpleName());
		logger.error("HomeController @postConstruct error");
		logger.debug("HomeController @postConstruct debug");
		logger.trace("HomeController @postConstruct trace");
		logger.warn("HomeController @postConstruct warn");
	}

	@GET
	@Path("all")
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
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}
	}

	@POST
	public Response insertPerson(final Person person) throws Exception {

		try {

			logger.info("Initiated invokeSessionBeanMethods method.");

			personContext.insert(person);

			return Response.ok(person).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}

	}

	public static void main(String[] args) {

	}
}
