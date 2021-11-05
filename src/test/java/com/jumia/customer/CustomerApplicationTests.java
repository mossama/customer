package com.jumia.customer;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import com.jumia.customer.dto.CountryDto;
import com.jumia.customer.dto.CustomerListResponse;
import com.jumia.customer.repository.CustomerRepository;
import com.jumia.customer.service.CountryService;
import com.jumia.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.SmartRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.AssertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CustomerService customerService;

    @Test
    void countryServiceTest() {
        CountryDto countryDto= countryService.searchForCounty("(212) 654642448");
        Assertions.assertTrue(countryDto.getCountryName().equalsIgnoreCase("Morocco"));
         Assertions.assertTrue(countryDto.getState().equalsIgnoreCase("Valid"));


        countryDto= countryService.searchForCounty("(258) 847651504");
        Assertions.assertTrue(countryDto.getCountryName()
                .equalsIgnoreCase("Mozambique"));

         countryDto= countryService.searchForCounty("(251) 9773199405");
        Assertions.assertTrue
                (countryDto.getCountryName().equalsIgnoreCase("Ethiopia"));

         countryDto= countryService.searchForCounty("(237) 677046616");
        Assertions.assertTrue(countryDto.getCountryName().equalsIgnoreCase("Cameroon"));

    }

    @Test
    void customerServiceTest() {
        CustomerListResponse customerListResponse = customerService.listCustomers(0);

        // Test count of 1 page
        Assertions.assertEquals(customerListResponse.getCustomerList().size()
                ,10);

    }

    @Test
    void testController() throws Exception {

        mvc.perform(get("/api/v1/customer?page=0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerList",IsCollectionWithSize.hasSize(10)));

    }
    @Test
    void testCountryApi() throws Exception {

        mvc.perform(get("/api/v1/customer/country/(212)?page=0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerList",IsCollectionWithSize.hasSize(7)));

        mvc.perform(get("/api/v1/customer/country/(212)?page=0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerList[1].country.countryName",is("Morocco")));

        mvc.perform(get("/api/v1/customer/country/(212)?page=0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerList[5].country.countryName",is("Morocco")));


    }
    @Test
    void testStateApi() throws Exception {

        mvc.perform(get("/api/v1/customer/country/(212)?page=0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerList",IsCollectionWithSize.hasSize(7)));

        mvc.perform(get("/api/v1/customer/country/(212)?page=0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.customerList[1].country.countryName",is("Morocco")));


    }






}
