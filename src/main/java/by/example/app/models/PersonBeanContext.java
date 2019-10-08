package by.example.app.models;

import by.example.app.infrastructure.persistence.PersonBeanLocalRepository;
import by.example.app.domain.Person;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PersonBeanContext implements PersonBeanLocalRepository {

	@PersistenceContext(unitName = "POOLMODE")
	private EntityManager em;

	public PersonBeanContext() {
	}

	// Получаем все пользователей с БД
	@Override
	public List<Person> getAll() {
		TypedQuery<Person> namedQuery = em.createNamedQuery("Person.getAll", Person.class);
		return namedQuery.getResultList();
	}

	// Получаем пользователя по id
	@Override
	public Person get(long id) {

		return em.find(Person.class, id);
	}

	// Добавляем Person-а В базу данных
	@Override
	public Person add(Person user) {

		return em.merge(user);
	}

	// обновляем пользователя
	// если Person которого мыпытаемся обновить нет,
	// то запишется он как новый
	@Override
	public void update(Person user) {

		add(user);
	}

	// удаляем Person по id
	@Override
	public void delete(long id) {

		em.remove(get(id));
	}

}
