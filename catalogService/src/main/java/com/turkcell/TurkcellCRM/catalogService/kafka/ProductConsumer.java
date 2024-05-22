package com.turkcell.TurkcellCRM.catalogService.kafka;

import com.turkcell.TurkcellCRM.catalogService.business.CatalogService;
import com.turkcell.TurkcellCRM.catalogService.entities.Product;
import com.turkcell.TurkcellCRM.commonPackage.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
    private final CatalogService catalogService;

    @KafkaListener(topics = "inventory-product-created",groupId="product-create")
    public void consume(ProductCreatedEvent productCreatedEvent){
        Product product = new Product();
       product.setDescription(productCreatedEvent.getDescription());
       product.setPrice(productCreatedEvent.getPrice());
       product.setTitle(productCreatedEvent.getTitle());
       product.setUnitOfStock(productCreatedEvent.getUnitOfStock());

        LOGGER.info(String.format("Product consumed =>%s",product.toString()));
        this.catalogService.add(product);
    }

}
