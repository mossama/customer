package com.jumia.customer.service;

import com.jumia.customer.constants.Constants;
import com.jumia.customer.dto.CustomerDto;
import com.jumia.customer.model.Customer;
import com.jumia.customer.dto.CustomerListResponse;
import com.jumia.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CountryService countryService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CountryService countryService) {
        this.customerRepository = customerRepository;
        this.countryService = countryService;
    }

    public CustomerListResponse listCustomers(Integer page){

        Pageable pageable = PageRequest.of(page, Constants.listItemsCount);
        Page<Customer> customerPage =
                customerRepository.findAll(pageable);

       List<CustomerDto> customerDtoList =  customerPage.getContent().stream()
               .map(customer->new CustomerDto(customer.getPhone(),customer.getName(),
                countryService.searchForCounty(customer.getPhone()))).collect(Collectors.toList());

        return new CustomerListResponse
                (customerDtoList,customerPage.getTotalPages());

    }
}
