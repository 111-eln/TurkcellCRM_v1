package com.turkcell.TurkcellCRM.productService.business.concretes;

import com.turkcell.TurkcellCRM.commonPackage.ProductCreatedEvent;
import com.turkcell.TurkcellCRM.productService.business.abstracts.ProductService;
import com.turkcell.TurkcellCRM.productService.business.dtos.CreateProductsRequest;
import com.turkcell.TurkcellCRM.productService.business.dtos.CreatedProductResponse;
import com.turkcell.TurkcellCRM.productService.business.rules.ProductServiceRules;
import com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.mapping.ModelMapperManager;
import com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.productService.dataAccess.ProductRepository;
import com.turkcell.TurkcellCRM.productService.entities.Product;
import com.turkcell.TurkcellCRM.productService.kafka.producers.ProductProducer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private ModelMapperService modelMapperService;
    private ProductRepository productRepository;
    private ProductProducer productProducer;
    private ProductServiceRules productServiceRules;

    @Transactional
    @Override
    public CreatedProductResponse add(CreateProductsRequest productsRequest) {

        productServiceRules.productAlreadyExists(productsRequest.getTitle());

        Product product=modelMapperService.forRequest().map(productsRequest, Product.class);

        Product dbProduct=productRepository.save(product);

        ProductCreatedEvent productCreatedEvent=modelMapperService.forResponse().map(dbProduct, ProductCreatedEvent.class);

        productProducer.sendMessage(productCreatedEvent);

        return modelMapperService.forResponse().map(dbProduct,CreatedProductResponse.class);
    }

    @Transactional
    @Override
    public void delete(int id) {

        productServiceRules.productShouldBeExists(id);

        Product product = this.productRepository.findById(id).get();
        product.setDeleted(true);
        product.setDeletedDate(LocalDateTime.now());

        this.productRepository.save(product);
    }

    @Transactional
    @Override
    public CreatedProductResponse update(CreateProductsRequest productRequest,int id) {

        productServiceRules.productShouldBeExists(id);

        Product product=modelMapperService.forRequest().map(productRequest, Product.class);

        Product dbProduct=productRepository.save(product);
        dbProduct.setId(id);

        return modelMapperService.forResponse().map(dbProduct,CreatedProductResponse.class);
    }

    @Override
    public boolean controlProductStock(String productTitle) {
        Product product=productRepository.findByTitle(productTitle).get();
        if(product.getUnitOfStock()>0){
            product.setUnitOfStock(product.getUnitOfStock()-1);
            product.setId(product.getId());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean controlProductTitle(String productTitle) {

        return productServiceRules.productIsExists(productTitle);
        //Product product=productRepository.findByTitle(productTitle).get();
        //return product != null;
    }

    @Override
    public List<CreatedProductResponse> getAll() {

        productServiceRules.productsNotExist();

        List<Product> products=productRepository.findAll();

        return products.stream().map(product ->
                modelMapperService.forResponse()
                        .map(product, CreatedProductResponse.class)).toList();    }

    @Override
    public CreatedProductResponse getById(int id) {

        productServiceRules.productShouldBeExists(id);

        return modelMapperService.forResponse().map(productRepository.findById(id), CreatedProductResponse.class);
    }
}
