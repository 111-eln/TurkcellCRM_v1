package com.turkcell.TurkcellCRM.productService.business.rules;

import com.turkcell.TurkcellCRM.productService.business.messages.Messages;
import com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.exceptions.types.BusinessException;
import com.turkcell.TurkcellCRM.productService.dataAccess.ProductRepository;
import com.turkcell.TurkcellCRM.productService.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceRules {
    private final ProductRepository productRepository;

    public void productShouldBeExists(int productId){
        Optional<Product> product = this.productRepository.findById(productId);
        if (product.isEmpty()){
            throw new BusinessException(Messages.ProductErrors.PRODUCT_NOT_FOUND);
        }
    }

    public void productAlreadyExists(String title){
        Optional<Product> product = this.productRepository.findByTitle(title);
        if (product.isPresent()){
            throw new BusinessException(Messages.ProductErrors.PRODUCT_ALREADY_EXISTS);
        }
    }

    public void productsNotExist(){
        List<Product> productList = this.productRepository.findAll();
        if (productList.isEmpty()){
            throw new BusinessException(Messages.ProductErrors.PRODUCTS_NOT_FOUND);
        }
    }

    public boolean productIsExists(String title){
        Optional<Product> product = this.productRepository.findByTitle(title);
        if (product.isEmpty()){
            throw new BusinessException(Messages.ProductErrors.PRODUCT_NOT_FOUND);
        }
        else {
            return true;
        }
    }
}
