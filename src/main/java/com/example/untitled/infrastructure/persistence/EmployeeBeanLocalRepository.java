package com.example.untitled.infrastructure.persistence;

import com.example.untitled.domain.Employee;

import javax.ejb.Local;
import java.util.List;

@Local //можно опустить, по умолчанию local
// компоненты EJB вообще могут обходиться без каких-либо интерфейсов.
public interface EmployeeBeanLocalRepository {

	Employee add(Employee user);

	List<Employee> getAll(Long start, Long from);

	Employee findById(long id);

	Employee update(Employee user);

	Employee patch(Employee user);

	void remove(long id);

	List<Employee> getAll();
}
