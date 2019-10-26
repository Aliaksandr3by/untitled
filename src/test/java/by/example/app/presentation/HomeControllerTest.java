package by.example.app.presentation;

import by.example.app.models.EmployeeBeanContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class HomeControllerTest {

	@EJB
	private EmployeeBeanContext cd;


	@Test
	public void cdShouldNotBeNull() {
		assertNotNull("should not be null", cd);
	}
}