package com.lightspeed.springboot.springboottest;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        customer = customerRepository.save(customer);
        log.info("Customer created with id {}", customer.getId());
        return customer;
    }

    public List<Customer> getAll() {
        List<Customer> all = Lists.newArrayList(customerRepository.findAll());
        log.info("All customers loaded ({} of them)", all.size());
        return all;
    }
}
