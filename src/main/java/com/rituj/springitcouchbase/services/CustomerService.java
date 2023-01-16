package com.rituj.springitcouchbase.services;

import com.rituj.springitcouchbase.entities.Customer;
import com.rituj.springitcouchbase.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

  private CustomerRepository customerRepository;

  public List<Customer> getCustomers() {
    return customerRepository.findAll();
  }

  public Customer saveCustomer(final Customer customer) {
    final var id = "Customer_" + UUID.randomUUID().toString();
    customer.setId(id);
    return customerRepository.save(customer);
  }
}
