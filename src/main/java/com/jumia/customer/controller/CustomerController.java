package com.jumia.customer.controller;

import com.jumia.customer.dto.CustomerDto;
import com.jumia.customer.dto.CustomerListResponse;
import com.jumia.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<CustomerListResponse> getAllCustomer(@RequestParam("page") Integer page){

        CustomerListResponse customerDtoList = customerService.listCustomers(page);

        return ResponseEntity.ok(customerDtoList);
    }


    @CrossOrigin("*")
    @GetMapping("/country/{countryCode}")
    public ResponseEntity<CustomerListResponse> getAllCustomerByCountry(@RequestParam("page") Integer page,
            @PathVariable("countryCode")@NotBlank String country){

        CustomerListResponse customerDtoList = customerService.listCustomersByCounty(page,country);


        return ResponseEntity.ok(customerDtoList);
    }
    @CrossOrigin("*")
    @GetMapping("/state/{state}")
    public ResponseEntity<CustomerListResponse> getAllCustomerByState(@RequestParam("page") Integer page,
                                                                                @PathVariable(value="state")@NotBlank String state){
        return ResponseEntity.ok(customerService.filterByState(state,page));
    }
    @CrossOrigin("*")
    @GetMapping("/country/{countryCode}/state/{state}")
    public ResponseEntity<CustomerListResponse> getAllCustomerByCountryAndState(@RequestParam("page") Integer page,
                                                                                @PathVariable(value = "countryCode")@NotBlank String country ,
                                                                                @PathVariable(value="state")@NotBlank String state){

        CustomerListResponse customerDtoList = customerService.listCustomersByCounty(page,country);

        customerDtoList = customerService.filterByState(customerDtoList.getCustomerList(),state);

        return ResponseEntity.ok(customerDtoList);
    }


}
