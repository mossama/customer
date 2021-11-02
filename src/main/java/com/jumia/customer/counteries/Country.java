package com.jumia.customer.counteries;

import com.jumia.customer.dto.CountryDto;

import java.util.regex.Pattern;

public abstract class Country {

    protected  String regex = "";

    protected  String countryName ="";

    protected String countryCode = "";

    protected int countryCodeLength = 4;

    public  CountryDto getCountryName(String number){

        if (number.matches(regex))
            return new CountryDto(countryName,"Valid");


        else if (number.substring(1,countryCodeLength).equals(countryCode))
            return new CountryDto(countryName,"Not Valid");

        return null;
        }

}
