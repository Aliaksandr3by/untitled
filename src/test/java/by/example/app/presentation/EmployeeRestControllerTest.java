package by.example.app.presentation;

import by.example.app.models.EmployeeBeanContext;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;


public class EmployeeRestControllerTest {

	private EmployeeBeanContext cd = new EmployeeBeanContext();


	@Test
	public void cdShouldNotBeNull() throws Exception {

		assertNotNull("should not be null", cd);
		//throw new Exception("test");
	}
}