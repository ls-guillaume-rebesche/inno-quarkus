package com.lightspeed.springboot.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer.getName());
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable("customerId") long customerId) {
        customerService.delete(customerId);
    }
}
