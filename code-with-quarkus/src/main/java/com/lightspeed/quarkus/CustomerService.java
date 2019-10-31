package com.lightspeed.quarkus;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CustomerService {

    @Inject
    EntityManager em;

    @Transactional
    public Customer createCustomer(String customerName) {
        Customer customer = new Customer();
        customer.setName(customerName);
        em.persist(customer);
        log.info("Customer created with id {}", customer.getId());
        return customer;
    }

    @Transactional
    public List<Customer> getAll() {
        List<Customer> resultList = em.createQuery("SELECT c FROM Customer c").getResultList();
        log.info("All customers loaded ({} of them)", resultList.size());
        return resultList;
    }

    @Transactional
    public void delete(long customerId) {
        em.remove(em.find(Customer.class, customerId));
        log.info("Deleted customer {}", customerId);
    }
}
