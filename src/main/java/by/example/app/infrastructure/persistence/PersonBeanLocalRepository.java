package by.example.app.infrastructure.persistence;

import by.example.app.domain.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonBeanLocalRepository {

	Person add(Person user);

	Person get(long id);

	void update(Person user);

	void delete(long id);

	List<Person> getAll();
}
