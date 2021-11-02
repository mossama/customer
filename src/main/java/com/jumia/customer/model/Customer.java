package com.jumia.customer.model;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "customer")

@Data
public class Customer {


    @Id
    private Integer id;

    private String phone;

    private String name;


}
