package by.example.app.presentation;

import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@ExtendWith(MockitoExtension.class)
public class EmployeeRestControllerTests {

	@EJB
	private EmployeeBeanLocalRepository employeeBean;

	@BeforeAll
	static void initAll() {
	}

	@BeforeEach
	void init() {
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}


	@Test
	@Disabled("for demonstration purposes")
	public void skippedTest() {
		// not executed
	}

	@DisplayName("fail")
	@Test
	public void failTest() {
		fail("failfailfailfailfail");
	}

	@DisplayName("should not be null")
	@Test
	public void cdShouldNotBeNull() {

		assertNotNull("should not be null", employeeBean);
	}
}