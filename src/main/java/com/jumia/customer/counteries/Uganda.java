package com.jumia.customer.counteries;

import org.springframework.stereotype.Component;

@Component
public class Uganda extends Country{

    Uganda(){
        regex="\\(256\\)\\ ?\\d{9}$";
        countryCode = "256";
        countryName="Uganda";
    }

}
