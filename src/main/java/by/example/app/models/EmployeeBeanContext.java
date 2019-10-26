package by.example.app.models;

import by.example.app.domain.Employee;
import by.example.app.exeptions.NotFoundException;
import by.example.app.infrastructure.persistence.EmployeeBeanLocalRepository;
import by.example.app.domain.Employee;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EmployeeBeanContext implements EmployeeBeanLocalRepository {

	@PersistenceContext(unitName = "POOLMODE")
	private EntityManager em;

	public EmployeeBeanContext() {
	}

	// Получаем все пользователей с БД
	@Override
	public List<Employee> getAll() {
		TypedQuery<Employee> namedQuery = em.createNamedQuery("Employee.getAll", Employee.class);
		return namedQuery.getResultList();
	}

	// Получаем пользователя по id
	@Override
	public Employee get(long id) {

		return em.find(Employee.class, id);
	}

	// Добавляем Employee-а В базу данных
	@Override
	public Employee add(Employee user) {

		return em.merge(user);
	}

	// обновляем пользователя
	// если Employee которого мыпытаемся обновить нет,
	// то запишется он как новый
	@Override
	public void update(Employee user) {

		add(user);
	}

	// удаляем Employee по id
	@Override
	public void delete(long id) {
		Employee employee = get(id);
		if(Objects.nonNull(employee)) {
			em.remove(employee);
		} else {
			throw new NotFoundException("Item is not found");
		}
	}

}
