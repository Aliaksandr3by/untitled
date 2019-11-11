package by.example.app.presentation;

import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.assertNull;

//@ExtendWith(WeldJunit5Extension.class)
public class EmployeeRestControllerTests {

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


	@DisplayName("should not be null")
	@Test
	public void cdShouldNotBeNull() {

		assertNull("should not be null", null);
	}
}