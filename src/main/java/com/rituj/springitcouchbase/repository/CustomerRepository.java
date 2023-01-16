package com.rituj.springitcouchbase.repository;

import com.rituj.springitcouchbase.entities.Customer;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CouchbaseRepository<Customer, String> {
}
