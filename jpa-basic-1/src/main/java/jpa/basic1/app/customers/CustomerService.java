package jpa.basic1.app.customers;

import jakarta.persistence.TypedQuery;
import jpa.app.configs.AppEntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
class CustomerService {

    private final AppEntityManager appEntityManager;


    public CustomerService(AppEntityManager appEntityManager) {
        this.appEntityManager = appEntityManager;
    }


    public List<Customer> all() {
        var entityManager = this.appEntityManager.entityManager();
        var customers = new ArrayList<Customer>();
        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            String query = "select cust from Customer cust";
            TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);
            customers = (ArrayList<Customer>) typedQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            log.info(" {}", e);
        } finally {
            entityManager.close();
        }

        return customers;
    }

    public Boolean add(Customer customer) {
        var isAdded = false;
        var entityManager = this.appEntityManager.entityManager();

        try {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
            isAdded = true;
        } catch (Exception e) {
            isAdded = false;
            log.info(" {}", e);
        } finally {
            entityManager.close();
        }

        return isAdded;
    }


}
