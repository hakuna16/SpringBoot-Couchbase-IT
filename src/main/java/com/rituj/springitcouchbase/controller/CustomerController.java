package com.rituj.springitcouchbase.controller;

import com.rituj.springitcouchbase.entities.Customer;
import com.rituj.springitcouchbase.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody final Customer customer){
        return customerService.saveCustomer(customer);
    }
}
