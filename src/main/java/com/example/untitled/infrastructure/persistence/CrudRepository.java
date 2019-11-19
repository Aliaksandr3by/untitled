package com.example.untitled.infrastructure.persistence;

import java.util.Collection;

public interface CrudRepository<T> {

	Collection<T> findAll(String sortOrder) throws Exception;

	T findById(T item) throws Exception;

	T insert(T item);

	T update(T item);

	T delete(T item);

	T patch(T item);
}
