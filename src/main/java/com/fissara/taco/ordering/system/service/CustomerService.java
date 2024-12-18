package com.fissara.taco.ordering.system.service;

import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

}
