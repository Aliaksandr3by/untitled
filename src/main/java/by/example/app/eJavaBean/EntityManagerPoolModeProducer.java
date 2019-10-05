package by.example.app.eJavaBean;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Named
@ApplicationScoped
public class EntityManagerPoolModeProducer {

	@Inject
	private Logger logger;

	@Inject
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	@PoolMode
	public EntityManager create() {

		return entityManagerFactory.createEntityManager();
	}

	public void dispose(@Disposes @PoolMode EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
			logger.info("entityManager.close()");
		}
	}

}
