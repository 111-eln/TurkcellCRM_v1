package com.turkcell.TurkcellCRM.catalogService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("filter-product")
public class Product {
    @Id
    private String id;

    @Field(name="title")
    private String title;

    @Field(name="description")
    private String description;

    @Field(name="price")
    private int price;

    @Field(name="unitOfStock")
    private int unitOfStock;
}
