package by.example.app.entity;

import by.example.app.exeptions.NotFoundException;
import by.example.app.exeptions.NotImplementedException;
import by.example.app.repositories.CrudRepository;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Named
@RequestScoped
public class PersonContext implements CrudRepository<Person> {

	private Logger logger;
	private EntityManagerFactory emf;

	Class<Person> clazz = Person.class;

	@PostConstruct
	public void init() {

		logger.info(PersonContext.class.getName() + " was initialized", this);
	}

	public PersonContext() {
	}

	@Inject
	public PersonContext(Logger logger, EntityManagerFactory emf) {
		this.logger = logger;
		this.emf = emf;
	}

	@PreDestroy
	public void destroy() {

		logger.info("it was destroyed");
	}

	@Override
	public List<Person> findAll(String sortOrder) throws Exception {

		EntityManager em = null;

		try {
			em = emf.createEntityManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

			CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(clazz);

			Root<Person> criteriaRoot = criteriaQuery.from(clazz);

			Path<String> id = criteriaRoot.get(sortOrder);

			criteriaQuery
					.select(criteriaRoot)
					.orderBy(criteriaBuilder.asc(id))
			;

			TypedQuery<Person> query = em.createQuery(criteriaQuery);

			List<Person> tmp = query.getResultList();

			return tmp;

		} catch (Exception e) {
			throw e;
		} finally {
			if (em != null && em.isOpen()) em.close();
		}

	}

	@Override
	public Person findById(Person item) {

		EntityManager em = null;

		try {

			em = emf.createEntityManager();

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

			CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(clazz);

			Root<Person> criteriaRoot = criteriaQuery.from(clazz);

			Path<String> id = criteriaRoot.get("id");

			criteriaQuery
					.select(criteriaRoot)
					.where(criteriaBuilder.equal(id, item.getId()))
			;

			TypedQuery<Person> query = em.createQuery(criteriaQuery);

			Person tmp = query.getSingleResult();

			return tmp;

		} catch (Exception e) {
			throw e;
		} finally {
			if (em != null && em.isOpen()) em.close();
		}

	}

	@Override
	public Person insert(Person item) {

		EntityManager em = null;
		EntityTransaction entityTransaction = null;

		try {

			em = emf.createEntityManager();

			entityTransaction = em.getTransaction();

			entityTransaction.begin();

			em.persist(item);

			entityTransaction.commit();

			return item;

		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if (em != null && em.isOpen()) em.close();
		}
	}

	@Override
	public Person delete(Person item) {

		EntityManager em = null;
		EntityTransaction entityTransaction = null;

		try {

			em = emf.createEntityManager();

			entityTransaction = em.getTransaction();

			entityTransaction.begin();

			Person tmp = em.find(clazz, item.getId());

			if (Objects.nonNull(tmp)) {
				em.remove(tmp);
				entityTransaction.commit();
			}

			return tmp;

		} catch (Exception e) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if (em != null && em.isOpen()) em.close();
		}
	}

	@Override
	public Person update(Person item) {
		throw new NotImplementedException("Method is not implemented");
	}

	@Override
	public Person patch(Person item) throws NotFoundException {
		throw new NotImplementedException("Method is not implemented");
	}
}
