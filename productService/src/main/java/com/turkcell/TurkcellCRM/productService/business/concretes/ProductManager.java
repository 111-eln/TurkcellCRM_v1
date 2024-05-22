package com.turkcell.TurkcellCRM.productService.business.concretes;

import com.turkcell.TurkcellCRM.commonPackage.ProductCreatedEvent;
import com.turkcell.TurkcellCRM.productService.business.abstracts.ProductService;
import com.turkcell.TurkcellCRM.productService.business.dtos.CreateProductsRequest;
import com.turkcell.TurkcellCRM.productService.business.dtos.CreatedProductResponse;
import com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.mapping.ModelMapperManager;
import com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.productService.dataAccess.ProductRepository;
import com.turkcell.TurkcellCRM.productService.entities.Product;
import com.turkcell.TurkcellCRM.productService.kafka.producers.ProductProducer;

import java.util.List;

public class ProductManager implements ProductService {
    private ModelMapperService modelMapperService;
    private ProductRepository productRepository;
    private ProductProducer productProducer;
    @Override
    public CreatedProductResponse add(CreateProductsRequest productsRequest) {
        Product product=modelMapperService.forResponse().map(productsRequest, Product.class);
        Product dbProduct=productRepository.save(product);
        ProductCreatedEvent productCreatedEvent=modelMapperService.forResponse().map(dbProduct, ProductCreatedEvent.class);
        productProducer.sendMessage(productCreatedEvent);
        return modelMapperService.forResponse().map(dbProduct,CreatedProductResponse.class);
    }

    @Override
    public void delete(int id) {

        productRepository.deleteById(id);
    }

    @Override
    public CreatedProductResponse update(CreateProductsRequest productRequest,int id) {
        Product product=modelMapperService.forResponse().map(productRequest, Product.class);
        Product dbProduct=productRepository.save(product);
        dbProduct.setId(id);
        return modelMapperService.forResponse().map(dbProduct,CreatedProductResponse.class);
    }

    @Override
    public List<CreatedProductResponse> getAll() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(product ->
                modelMapperService.forResponse()
                        .map(product, CreatedProductResponse.class)).toList();    }

    @Override
    public CreatedProductResponse getById(int id) {
        return modelMapperService.forResponse().map(productRepository.findById(id), CreatedProductResponse.class);
    }
}
