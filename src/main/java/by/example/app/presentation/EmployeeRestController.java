package by.example.app.presentation;

import by.example.app.domain.Employee;
import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("employees")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class EmployeeRestController {

	@Inject
	private transient Logger logger;

	@Inject
	ValidatorFactory validatorFactory;
	@Inject
	Validator validator;

	@EJB
	private EmployeeBeanLocalRepository employeeBean;

	@PostConstruct
	private void postConstruct() {
		logger.info("@postConstruct EmployeeRestController");
	}

	public EmployeeRestController() {
	}

	public EmployeeRestController(Logger logger, EmployeeBeanLocalRepository EmployeeBean) {
		this.logger = logger;
		this.employeeBean = EmployeeBean;
	}

	@PreDestroy
	private void preDestroy() {

		logger.info("@preDestroy EmployeeRestController");
	}

	ExecutorService executor = Executors.newFixedThreadPool(10);

	@GET
	@Path("suspended")
	public void getEmployees(@Suspended final AsyncResponse ar) {

		executor.submit(() -> {
			List<Employee> response = employeeBean.getAll();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ar.resume(response);
		});

	}

	@GET
	public Response getEmployees() {
		try {

			logger.info("Initiated getEmployees method");

			List<Employee> peoples = employeeBean.getAll();

			return Response.status(Response.Status.OK).entity(peoples).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("selected")
	public Response getSelected(@QueryParam("start") int from, @QueryParam("page") int page) {
		try {

			logger.info("Initiated getSelected method");

			List<Employee> peoples = employeeBean.getAll();

			return Response.status(Response.Status.OK).entity(peoples).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("{id}")
	public Response getEmployeeById(@PathParam("id") Long id) {
		try {

			logger.info("Initiated getEmployee method.");

			Employee employee = employeeBean.findById(id);

			logger.info(employee.toString());

			return Response.ok(employee).status(Response.Status.OK).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}
	}

	@POST
	public Response createEmployee(final Employee employee) {

		try {

			logger.info("Initiated insertEmployee method.");

			employeeBean.add(employee);

			return Response.status(Response.Status.CREATED).build();

		} catch (javax.ejb.EJBException e) {

			logger.error(e.getCause().getMessage(), e.getCause());

			if(e.getCause() instanceof javax.persistence.PersistenceException){

				PSQLException ex = (PSQLException)e.getCause().getCause().getCause();
				return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage().toString()).build();
			}

			Set<ConstraintViolation<Employee>> cv = validator.validate(employee);

			if (Objects.nonNull(cv)){
				return Response.status(Response.Status.BAD_REQUEST).entity(cv).build();
			}

			throw e;

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}

	}

	@PUT
	@Path("{id}")
	public Response putEmployeeById(final Employee employee) {

		try {

			logger.info("Initiated putEmployeeById method.");

			employeeBean.update(employee);

			return Response.status(Response.Status.CREATED).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause().getMessage()).build();
		}

	}

	@PATCH
	public Response patchPartEmployeeById(final Employee employee) {

		try {

			logger.info("Initiated patchPartEmployeeById method.");

			employeeBean.update(employee);

			return Response.status(Response.Status.CREATED).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause().getMessage()).build();
		}

	}

	@DELETE
	@Path("{id}")
	public Response deleteEmployeeById(@PathParam("id") final Long id) {

		try {

			logger.info("Initiated deleteEmployeeById method.");

			employeeBean.remove(id);

			return Response.status(Response.Status.NO_CONTENT).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause().getMessage()).build();
		}

	}

	public static void main(String[] args) {

	}

	@GET
	@Path("context")
	public Response context(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {

		logger.info(request);
		logger.info(response);
		return Response.ok().build();
	}
}
