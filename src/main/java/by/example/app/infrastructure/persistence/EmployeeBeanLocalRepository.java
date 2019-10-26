package by.example.app.infrastructure.persistence;

import by.example.app.domain.Employee;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EmployeeBeanLocalRepository {

	Employee add(Employee user);

	Employee get(long id);

	void update(Employee user);

	void delete(long id);

	List<Employee> getAll();
}
