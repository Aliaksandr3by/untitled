package by.example.app.eJavaBean;

import by.example.app.entity.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PersonBean implements PesonBeanLocal<Person> {

	//	@PersistenceUnit(unitName = "CRM")
	private EntityManagerFactory emf;

	@PersistenceContext(unitName = "DEVMODE")
	private EntityManager em;

	public PersonBean() {

	}


	// Добавляем Person-а В базу данных
	public Person add(Person user) {
		return em.merge(user);
	}

	// Получаем пользователя по id
	public Person get(long id) {
		return em.find(Person.class, id);
	}

	// обновляем пользователя
	// если Person которого мыпытаемся обновить нет,
	// то запишется он как новый
	public void update(Person user) {
		add(user);
	}

	// удаляем Person по id
	public void delete(long id) {
		em.remove(get(id));
	}

	// Получаем все пользователей с БД
	public List<Person> getAll() {
		TypedQuery<Person> namedQuery = em.createNamedQuery("Person.getAll", Person.class);
		return namedQuery.getResultList();
	}


}
