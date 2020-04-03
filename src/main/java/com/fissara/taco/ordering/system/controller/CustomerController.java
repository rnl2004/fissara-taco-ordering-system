package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.dto.CustomerRequest;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer create(@RequestBody CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setId(customerRequest.getId());
        customer.setName(customerRequest.getName());
        return customerService.save(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }
}
