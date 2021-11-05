package com.jumia.customer.repository;

import com.jumia.customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer> {

    Page<Customer> findAllByPhoneLike(String phone,Pageable pageable);

}
