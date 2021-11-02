package com.jumia.customer.repository;

import com.jumia.customer.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer> {
}
