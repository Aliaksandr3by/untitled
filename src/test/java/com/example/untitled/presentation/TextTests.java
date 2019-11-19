package com.example.untitled.presentation;

import com.example.untitled.infrastructure.config.LoggerProducer;
import com.example.untitled.infrastructure.persistence.EmployeeBeanLocalRepository;
import com.example.untitled.models.EmployeeBeanContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@Ignore
@RunWith(Arquillian.class)
public class TextTests {

	@Inject
	private EmployeeBeanLocalRepository employeeBean;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(EmployeeRestController.class, EmployeeBeanContext.class, LoggerProducer.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void employeeBeanShouldNotBeNull() {

		assertNotNull(employeeBean);
	}

}
