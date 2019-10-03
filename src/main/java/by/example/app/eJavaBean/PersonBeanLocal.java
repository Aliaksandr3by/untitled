package by.example.app.eJavaBean;

import by.example.app.entity.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonBeanLocal<T> {

	// Добавляем Person-а В базу данных
	Person add(Person user);

	// Получаем пользователя по id
	Person get(long id);

	// обновляем пользователя
	// если Person которого мыпытаемся обновить нет,
	// то запишется он как новый
	void update(Person user);

	// удаляем Person по id
	void delete(long id);

	// Получаем все пользователей с БД
	List<Person> getAll();
}
