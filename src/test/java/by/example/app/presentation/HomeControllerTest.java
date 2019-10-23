package by.example.app.presentation;

import by.example.app.models.PersonContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class HomeControllerTest {

	@Inject
	private PersonContext cd;


	@Test
	public void cdShouldNotBeNull() {
		assertNotNull("should not be null", cd);
	}
}