package com.jumia.customer.counteries;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Mozambique extends Country{

    Mozambique(){
        regex="\\(258\\)\\ ?[28]\\d{7,8}$";
        countryCode = "258";
        countryName="Mozambique";
    }

}
