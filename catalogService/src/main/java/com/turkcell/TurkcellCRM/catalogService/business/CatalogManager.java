package com.turkcell.TurkcellCRM.catalogService.business;

import com.turkcell.TurkcellCRM.catalogService.entities.Product;
import com.turkcell.TurkcellCRM.catalogService.repositories.CatalogRepository;

import java.util.List;

public class CatalogManager implements CatalogService{
    private CatalogRepository catalogRepository;
    @Override
    public void add(Product product) {
        catalogRepository.save(product);

    }

    @Override
    public List<Product> getAll() {
        return catalogRepository.findAll();
    }
}
