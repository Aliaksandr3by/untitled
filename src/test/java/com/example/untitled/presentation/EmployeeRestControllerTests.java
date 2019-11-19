package com.example.untitled.presentation;

import com.example.untitled.domain.Employee;
import com.example.untitled.domain.Gender;
import com.example.untitled.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.junit.jupiter.api.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
			LocalDate.of(2019, 11, 14)
	);

	@EJB
	private EmployeeBeanLocalRepository employeeBean;

	private EJBContainer ejbContainer;

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

	@Disabled
	@DisplayName("employeeBean should not be isEmpty")
	@Test
	public void employeeBeanShouldNotBeNull() {
		assertNotNull(employeeBean);
	}

	@DisplayName("validate should not be isEmpty")
	@Test
	public void validatorShouldNotBeNull() {

		Set<ConstraintViolation<Employee>> cv = validator.validate(employee);

		for (ConstraintViolation<Employee> tmp : cv) {
			System.out.println(tmp.getMessage());
		}
		assertTrue(cv.isEmpty());
	}
}