package by.example.app.presentation;

import by.example.app.domain.Employee;
import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employees")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class EmployeeRestController {

	@Inject
	private transient Logger logger;

	@EJB
	private EmployeeBeanLocalRepository EmployeeBean;

	public EmployeeRestController() {
	}

	public EmployeeRestController(Logger logger, EmployeeBeanLocalRepository EmployeeBean) {
		this.logger = logger;
		this.EmployeeBean = EmployeeBean;
	}

	@PostConstruct
	private void postConstruct() {
		logger.info("@postConstruct EmployeeRestController");
	}

	@PreDestroy
	private void preDestroy() {

		logger.info("@preDestroy EmployeeRestController");
	}

	@GET
	@Path("employees")
	public Response getEmployees() {
		try {

			logger.info("Initiated getEmployees method");

			List<Employee> peoples = EmployeeBean.getAll();

			return Response.status(Response.Status.OK).entity(peoples).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("{id}")
	public Response getEmployee(@PathParam("id") Long id) {
		try {

			logger.info("Initiated getEmployee method.");

			Employee employee = EmployeeBean.get(id);

			logger.info(employee.toString());

			return Response.ok(employee).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}
	}

	@POST
	public Response insertEmployee(final Employee employee) {

		try {

			logger.info("Initiated insertEmployee method.");

			EmployeeBean.add(employee);

			return Response.status(Response.Status.CREATED).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}

	}

	@DELETE
	@Path("{id}")
	public Response deleteEmployee(@PathParam("id") final Long id) {

		try {

			logger.info("Initiated deleteEmployee method.");

			EmployeeBean.delete(id);

			return Response.status(Response.Status.NO_CONTENT).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause().getMessage()).build();
		}

	}

	public static void main(String[] args) {

	}
}
