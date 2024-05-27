package com.TurkcellCRM.basketService.services.abstracts;


import com.TurkcellCRM.basketService.entities.Basket;

import java.util.Map;

public interface BasketService {
    void add(String customerId,String productId);
    Map<String, Basket> getAllItems();
}
