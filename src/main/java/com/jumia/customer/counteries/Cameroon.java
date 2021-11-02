package com.jumia.customer.counteries;

import org.springframework.stereotype.Component;

@Component
public class Cameroon extends Country{

    Cameroon(){
        regex="\\(237\\)\\ ?[2368]\\d{7,8}$";
        countryCode = "237";
        countryName="Cameroon";
    }

}
