package by.example.app.eJavaBean;

import by.example.app.entity.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonBeanLocal {

	Person add(Person user);

	Person get(long id);

	void update(Person user);

	void delete(long id);

	List<Person> getAll();
}
