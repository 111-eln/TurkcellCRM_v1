package com.turkcell.TurkcellCRM.orderService.clients;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="individualcustomerservice",url = "http://localhost:9009/api/v1/customers")
public class TakeIndividualCustomerIdClient {
    //    @PostMapping("/getCustomer")
//    @PostMapping("/getCustomer")
    @PostMapping("/getCustomer")
    boolean getCustomerId(@RequestBody Integer id) {
        return false;
    }
}
