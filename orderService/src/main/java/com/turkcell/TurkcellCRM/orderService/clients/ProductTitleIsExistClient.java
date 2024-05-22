package com.turkcell.TurkcellCRM.orderService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name="productservice",url="http://localhost:9029/productservice/api/v1/products")

public interface ProductTitleIsExistClient {
    @PutMapping("/productTitleControl")
    public boolean controlProductTitle(@RequestBody String productTitle);

    }
