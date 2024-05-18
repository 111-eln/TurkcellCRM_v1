package com.turkcell.TurkcellCRM.orderService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="individualcustomerservice",url="http://localhost:9009/individualcustomerservice/api/v1/customers")
public interface IsCustomerExistClient {
    @GetMapping("/{id}")
    public boolean getById2(@PathVariable int id);

}
