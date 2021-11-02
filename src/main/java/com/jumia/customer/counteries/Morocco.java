package com.jumia.customer.counteries;

import org.springframework.stereotype.Component;

@Component
public class Morocco extends Country{

    Morocco(){
        regex= "\\(212\\)\\ ?[5-9]\\d{8}$";
        countryCode = "212";
        countryName="Morocco";
    }

}
