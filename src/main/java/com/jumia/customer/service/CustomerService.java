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

    public Pageable getPageableObject(Integer page)
    {

        if (page==-1) return Pageable.unpaged();
            return PageRequest.of(page, Constants.listItemsCount);
    }

    public CustomerListResponse listCustomers(Integer page){

        Pageable pageable = getPageableObject(page);

        Page<Customer> customerPage =
                customerRepository.findAll(pageable);

        return new CustomerListResponse
                (getCustomerCountryAndState(customerPage),customerPage.getTotalPages());
    }

    public CustomerListResponse listCustomersByCounty(Integer page,String countryCode){

        Pageable pageable = getPageableObject(page);

        Page<Customer> customerPage =
                customerRepository.findAllByPhoneLike(countryCode+Constants.likeOperator,pageable);

        return new CustomerListResponse
                (getCustomerCountryAndState(customerPage),customerPage.getTotalPages());
    }

    public List<CustomerDto> getCustomerCountryAndState(Page<Customer> customerPage){
        return customerPage.getContent().stream()
                .map(customer->new CustomerDto(customer.getPhone(),customer.getName(),
                        countryService.searchForCounty(customer.getPhone()))).collect(Collectors.toList());

    }

    public  CustomerListResponse filterByCountry(List<CustomerDto> customerDtoList,String country){

        List<CustomerDto> customerDtos = customerDtoList.stream().filter(c->c.getCountry().getCountryName().contains(country)).collect(Collectors.toList());
        Integer pages = customerDtos.size()/Constants.listItemsCount;

        return  new CustomerListResponse
                (customerDtos,pages);

    }
    public  CustomerListResponse filterByState(List<CustomerDto> customerDtoList,String state){

        List<CustomerDto> customerDtos = customerDtoList.stream().filter(c->c.getCountry().getState().equalsIgnoreCase(state)).collect(Collectors.toList());
        Integer pages = customerDtos.size()/Constants.listItemsCount+1;

        return  new CustomerListResponse
                (customerDtos,pages);

    }

    public CustomerListResponse filterByState(String state,Integer page){


        Pageable pageable = getPageableObject(-1);
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerDto> customerDtoList = getCustomerCountryAndState(customers).stream().filter(c->c.getCountry().getState().equalsIgnoreCase(state)).collect(Collectors.toList());
        Integer pages = customerDtoList.size()/Constants.listItemsCount+1;

        return new CustomerListResponse(customerDtoList.stream()
                .skip(page*Constants.listItemsCount).limit(Constants.listItemsCount)
                .collect(Collectors.toList()), pages);
    }
}
