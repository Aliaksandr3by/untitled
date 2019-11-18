package by.example.app.presentation;

import by.example.app.domain.Employee;
import by.example.app.domain.Gender;
import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.jboss.weld.context.ejb.Ejb;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ExtendWith(WeldJunit5AutoExtension.class)
public class EmployeeRestControllerTests {

	private Employee employee = new Employee(
			"fn" + Math.random() * 20,
			"ln" + Math.random() * 20,
			1,
			"jt" + Math.random() * 20,
			Gender.FEMALE,
			LocalDate.of(2019, 11, 14)
	);

	@Ejb
	private EmployeeBeanLocalRepository employeeBean;

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