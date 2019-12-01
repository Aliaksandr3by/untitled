package com.example.untitled.presentation;

import com.example.untitled.domain.Employee;
import com.example.untitled.domain.Gender;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//@ExtendWith(Arquillian.class)
public class EmployeeRestControllerTests {

	private Employee employee = new Employee(
			"fn" + Math.random() * 20,
			"ln" + Math.random() * 20,
			1,
			"jt" + Math.random() * 20,
			Gender.FEMALE,
			LocalDate.of(2019, 1, 1)
	);

	@BeforeAll
	static void setUp() throws NamingException {

	}

	@BeforeEach
	void init() throws NamingException {

	}

	@AfterEach
	void tearDownAfterEach() {
	}

	@AfterAll
	static void tearDownAll() {

	}

	@DisplayName("validate should not be isEmpty")
	@Test
	public void validatorShouldNotBeNull() {

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> cv = validator.validate(employee);

		for (ConstraintViolation<Employee> tmp : cv) {
			System.out.println(tmp.getMessage());
		}
		assertTrue(cv.isEmpty());
	}

	@DisplayName("ClientBuilder")
	@Test
	public void asdd() {

		Client client = ClientBuilder.newClient();

		Employee order = client
				.target("http://localhost:8080/untitled/api/employees/")
				.path("{id}")
				.resolveTemplate("id", 32)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class).readEntity(Employee.class);

		assertNotNull(order);
	}
}