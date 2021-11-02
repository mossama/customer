package com.jumia.customer.controller;

import com.jumia.customer.dto.CustomerListResponse;
import com.jumia.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(customerService.listCustomers(page));
    }

}
