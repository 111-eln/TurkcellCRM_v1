package com.turkcell.TurkcellCRM.catalogService.repositories;

import com.turkcell.TurkcellCRM.catalogService.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatalogRepository extends MongoRepository<Product,String> {
}
