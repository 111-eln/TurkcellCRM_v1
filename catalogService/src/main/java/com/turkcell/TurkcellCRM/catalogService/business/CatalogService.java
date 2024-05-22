package com.turkcell.TurkcellCRM.catalogService.business;

import com.turkcell.TurkcellCRM.catalogService.entities.Product;

import java.util.List;

public interface CatalogService {
    void  add(Product product);
    List<Product> getAll();
}
