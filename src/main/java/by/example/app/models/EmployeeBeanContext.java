package by.example.app.models;

import by.example.app.domain.Employee;
import by.example.app.exeptions.NotFoundException;
import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;


@Stateless
public class EmployeeBeanContext implements EmployeeBeanLocalRepository {

	private EntityManager entityManager;

	@PersistenceContext(unitName = "POOLMODE")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EmployeeBeanContext() {
	}

	@Override
	public List<Employee> getAll() {
		final TypedQuery<Employee> namedQuery = entityManager.createNamedQuery("Employee.getAll", Employee.class);
		return namedQuery.getResultList();
	}

	@Override
	public Employee findById(long id) {

		return entityManager.find(Employee.class, id);
	}

	@Override
	public Employee add(Employee user) {
		try {
			entityManager.persist(user);
			return user;
		} catch (PersistenceException e) {
			throw e;
		}
	}

	@Override
	public Employee update(Employee user) {

		Employee employee = findById(user.getEmployeeId());

		Employee employeePatched = entityManager.merge(employee.patcherEmployee(user));

		return employeePatched;
	}

	@Override
	public void remove(long id) {

		Employee employee = findById(id);

		if (Objects.nonNull(employee)) {
			entityManager.remove(employee);
		} else {
			throw new NotFoundException("Item is not found");
		}
	}

}
