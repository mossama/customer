package com.jumia.customer.service;

import com.jumia.customer.counteries.Country;
import com.jumia.customer.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {

    Map<String, Country> countries;

    @Autowired
    public CountryService(Map<String, Country> countries) {
        this.countries = countries;
    }

    public CountryDto  searchForCounty(String phone){

        CountryDto country = countries.entrySet().stream()
                .map(e->e.getValue().getCountryName(phone)).
                filter(countryDto -> countryDto!=null).findAny().get();

        return country;
    }






}
