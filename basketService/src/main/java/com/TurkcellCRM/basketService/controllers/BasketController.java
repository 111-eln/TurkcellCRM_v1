package com.TurkcellCRM.basketService.controllers;

import com.TurkcellCRM.basketService.entities.Basket;
import com.TurkcellCRM.basketService.services.abstracts.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping
    public void addItem(@RequestParam String customerId,@RequestParam String productId){
        basketService.add(customerId,productId);
    }
    @GetMapping
    public Map<String, Basket> getAllItems(){
        return basketService.getAllItems();
    }
}
