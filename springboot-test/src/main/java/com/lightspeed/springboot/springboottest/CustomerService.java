package com.lightspeed.springboot.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private EntityManager em;

    @Transactional
    public Customer create(String name) {
        Customer customer = new Customer();
        customer.setName(name);
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
