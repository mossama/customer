package com.jumia.customer.dto;

import com.jumia.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListResponse {

    private List<CustomerDto> customerList;
    private Integer totalNumber;

}
