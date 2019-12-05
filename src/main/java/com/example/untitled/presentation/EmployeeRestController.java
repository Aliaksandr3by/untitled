package com.example.untitled.presentation;

import com.example.untitled.domain.Employee;
import com.example.untitled.exeptions.NotFoundException;
import com.example.untitled.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Providers;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Path("employees")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes({"application/xml; qs=0.75", "application/json; qs=1"})
public class EmployeeRestController {

	@Inject
	private transient Logger logger;

	@Inject
	ValidatorFactory validatorFactory;

	@Inject
	Validator validator;

	@EJB
	private EmployeeBeanLocalRepository employeeBean;

	@Context
	Application app;
	@Context
	UriInfo uri;
	@Context
	HttpHeaders headers;
	@Context
	Request request;
	@Context
	SecurityContext security;
	@Context
	Providers providers;

	private ExecutorService executor = Executors.newFixedThreadPool(10);

	@PostConstruct
	private void postConstruct() {
		logger.info("@postConstruct EmployeeRestController");
	}

	public EmployeeRestController() {
	}

	@PreDestroy
	private void preDestroy() {

		logger.info("@preDestroy EmployeeRestController");
		executor.shutdown();
	}

	@GET
	@Path("suspended")
	public void getEmployees(@Suspended final AsyncResponse ar) {

		ar.register((CompletionCallback) ((Throwable ex) -> {
			logger.info("onComplete");
			if (Objects.nonNull(ex)) logger.error(ex.getMessage(), ex);
		}));

		ar.register((ConnectionCallback) ((AsyncResponse ar1) -> {
			logger.info("onDisconnect");
			if (ar1.isCancelled()) logger.info("isCancelled");
		}));

		executor.submit(() -> {

			List<Employee> peoples = employeeBean.getAll();

			try {
				TimeUnit.SECONDS.sleep(6);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			ar.resume(peoples);
		});

	}

	@GET
	public Response getEmployees() {
		try {

			logger.info("getEmployees()");

			List<Employee> employee = employeeBean.getAll();

			if (!employee.isEmpty()) {
				return Response.ok(employee).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e);
			throw e;
		}
	}

	@GET
	@Path("selected")
	public Response getSelected(@QueryParam("start") Long from, @QueryParam("page") Long page) {
		try {

			logger.info("Initiated getSelected method");

			List<Employee> employee = employeeBean.getAll(from, page);

			if (Objects.nonNull(employee)) {
				return Response.ok(employee).status(Response.Status.OK).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

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

			if (Objects.nonNull(employee)) {
				return Response.ok(employee).status(Response.Status.OK).build();
			} else {
				throw new NotFoundException("Item was not found");
				//return Response.status(Response.Status.NOT_FOUND).build();
			}

		} catch (NotFoundException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@POST
	public Response createEmployee(final Employee employee) {

		Set<ConstraintViolation<Employee>> cv;

		try {
			employee.setEmployeeId(null);
			cv = validator.validate(employee);

			if (cv.isEmpty()) {

				employeeBean.add(employee);

				logger.info(employee.toString());

				return Response.status(Response.Status.CREATED).build();

			} else {

				ConstraintViolation<Employee> tmp = cv.stream().findFirst().get();
				return Response.status(Response.Status.BAD_REQUEST).entity(tmp.getMessage()).build();

			}

		} catch (javax.ejb.EJBException e) {

			logger.error(e.getCause().getMessage(), e.getCause());

			if (e.getCause() instanceof javax.persistence.PersistenceException) {

				PSQLException ex = (PSQLException) e.getCause().getCause().getCause();
				return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
			}

			throw e;

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			throw e;
		}

	}

	/**
	 * @param employee
	 * @return
	 */
	@PATCH
	public Response patchEmployeeById(final Employee employee) {

		try {

			logger.info("Initiated putEmployeeById method.");

			employeeBean.patch(employee);

			return Response.status(Response.Status.CREATED).build();

		} catch (Throwable e) {
			logger.error(e.getCause().getMessage(), e.getCause());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getCause().getMessage()).build();
		}

	}

	@PUT
	public Response putPartEmployeeById(final Employee employee) {

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

	@HEAD
	public void headOrder() {
		logger.info("headOrder");
	}

	@GET
	@Path("context")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response context(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws InterruptedException, ExecutionException {

		Object lock = new Object();
		Lock reentrantLock = new ReentrantLock();

		FutureTask<String> future = new FutureTask<>(() -> {
			synchronized (lock) {
				try {
					reentrantLock.lock();
					Thread.sleep(3000);
					System.out.println("wait " + Thread.currentThread().getName() + Thread.currentThread().getState());
					Thread.sleep(3000);
					lock.wait();
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			}
			System.out.println("thread");
			reentrantLock.unlock();
			return "q1";
		});

		Executor executor = (runnable) -> {
			new Thread(runnable).start();
		};

		executor.execute(future);

		// Ждём и после этого забираем себе лок, оповещаем и отдаём лок
		Thread.sleep(3000);

		System.out.println("main " + Thread.currentThread().getName() + Thread.currentThread().getState());

		synchronized (lock) {
			lock.notify();
		}

		return Response.ok(future.get()).build();
	}

}


