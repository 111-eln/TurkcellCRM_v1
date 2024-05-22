package com.turkcell.TurkcellCRM.productService.dataAccess;

import com.turkcell.TurkcellCRM.productService.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
