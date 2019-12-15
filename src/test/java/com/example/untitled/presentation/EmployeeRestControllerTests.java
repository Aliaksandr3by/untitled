package com.example.untitled.presentation;

import com.example.untitled.domain.Employee;
import com.example.untitled.domain.Gender;
import com.example.untitled.infrastructure.filters.ClientFilter;
import com.example.untitled.infrastructure.filters.CorsFilter;
import com.example.untitled.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EmployeeRestControllerTests {

	private Employee employee = new Employee(
			"fn" + Math.random() * 20,
			"ln" + Math.random() * 20,
			1,
			"jt" + Math.random() * 20,
			Gender.FEMALE,
			LocalDate.of(2019, 1, 1)
	);

	@Inject
	private transient Logger logger;

	//TODO
	@EJB
	private EmployeeBeanLocalRepository employeeBean;

	public EmployeeRestControllerTests() {
	}

	@BeforeAll
	static void setUp() throws NamingException {

	}

	@BeforeEach
	void init() throws NamingException {

	}

	@DisplayName("validate should not be isEmpty")
	@Test
	public void validatorTest() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> cv = validator.validate(employee);

		for (ConstraintViolation<Employee> tmp : cv) {
			System.out.println(tmp.getMessage());
		}
		assertTrue(cv.isEmpty());
	}

	@DisplayName("ClientBuilder")
	@Test
	public void clientTest() {

		Client client = ClientBuilder.newClient();
		client.register(ClientFilter.class);

		var order2 = client.target("http://localhost:8080/untitled-1.5-SNAPSHOT/api/employees/")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(employee, "application/json"));

		var order = client.target("http://localhost:8080/untitled-1.5-SNAPSHOT/api/employees/")
				.path("{id}")
				.resolveTemplate("id", 1)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(Employee.class);

		System.out.println(order.toString());
		assertNotNull(order);
		assertNotNull(order2);

	}

	@AfterEach
	void tearDownAfterEach() {
	}

	@AfterAll
	static void tearDownAll() {

	}
}