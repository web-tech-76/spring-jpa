package jpa.basic1.app.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AppEntityManager {

    private final PersistenceUnitInfo persistenceUnitInfo;


    public AppEntityManager(PersistenceUnitInfo persistenceUnitInfo) {
        this.persistenceUnitInfo = persistenceUnitInfo;
    }


    private EntityManagerFactory entityManagerFactory() {
        var emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new AppPersistenceUnitInfo(), new HashMap<>());

        return emf;
    }

    public EntityManager entityManager() {
        var emf = entityManagerFactory();
        return emf.createEntityManager();
    }


}
