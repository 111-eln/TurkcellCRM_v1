package com.turkcell.TurkcellCRM.orderService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="individualcustomerservice",url = "http://localhost:9009/api/v1/customers")
public interface TakeIndividualCustomerIdClient {
//    @PostMapping("/getCustomer")
//    @PostMapping("/getCustomer")
    @PostMapping("/getCustomer/{id}")
    boolean getCustomerId(@PathVariable int id) ;
}
