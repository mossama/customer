package com.jumia.customer.counteries;

import org.springframework.stereotype.Component;

@Component
public class Ethiopia extends Country{

    Ethiopia(){
        regex="\\(251\\)\\ ?[1-59]\\d{8}$";
        countryCode = "251";
        countryName="Ethiopia";
    }

}
