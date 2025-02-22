package com.turkcell.TurkcellCRM.orderService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="productservice",url="http://localhost:9029/productservice/api/v1/products")

public interface ProductStockIsEnoughClient {
    @PutMapping("/productStockControl")
    public boolean controlProductStock(@RequestBody String productTitle);


    }
