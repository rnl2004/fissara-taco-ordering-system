package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.dto.CustomerRequest;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestBody CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        return customerService.create(customer);
    }

    @GetMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }
}
