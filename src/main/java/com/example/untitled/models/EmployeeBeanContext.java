package com.example.untitled.models;

import com.example.untitled.domain.Employee;
import com.example.untitled.exeptions.NotFoundException;
import com.example.untitled.infrastructure.persistence.EmployeeBeanLocalRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;


@Stateless
public class EmployeeBeanContext implements EmployeeBeanLocalRepository {

	private EntityManager entityManager;

	@PersistenceContext(unitName = "wildfly")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EmployeeBeanContext() {
	}

	@Override
	public List<Employee> getAll() {
		final TypedQuery<Employee> namedQuery = entityManager.createNamedQuery("getAll", Employee.class);
		return namedQuery.getResultList();
	}

	@Override
	public List<Employee> getAll(Long start, Long from) {
		final TypedQuery<Employee> namedQuery =
				entityManager.createNamedQuery("getAllStartFrom", Employee.class)
						.setParameter("startId", start)
						.setParameter("fromId", from);

		return namedQuery.getResultList();
	}

	@Override
	public Employee findById(long id) {

		return entityManager.find(Employee.class, id);
	}

	@Override
	public Employee add(Employee user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public Employee update(Employee user) {

		Employee employee = findById(user.getEmployeeId());

		return entityManager.merge(employee.patcherEmployee(user));
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
