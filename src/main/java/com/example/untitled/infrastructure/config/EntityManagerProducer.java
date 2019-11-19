package com.example.untitled.infrastructure.config;

import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;

@Deprecated
@ApplicationScoped
public class EntityManagerProducer {

	@Inject
	private Logger logger;

	private EntityManagerFactory entityManagerFactory;

	@PostConstruct
	private void postConstruct() {
		logger.info("@postConstruct EntityManagerProducer");
	}

	public EntityManagerProducer() {

	}

	@Inject
	public EntityManagerProducer(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@PreDestroy
	private void preDestroy(){
		logger.info("@preDestroy EntityManagerProducer");
	}

	@Produces
	@RequestScoped
	@CRMMode
	public EntityManager create() {

		return entityManagerFactory.createEntityManager();
	}

	public void dispose(@Disposes @CRMMode EntityManager entityManager) {
		if (Objects.nonNull(entityManager) && entityManager.isOpen()) {
			entityManager.close();
			logger.info("entityManager.close()");
		} else {
			logger.info("Objects entityManager is Null");
		}
	}

}
