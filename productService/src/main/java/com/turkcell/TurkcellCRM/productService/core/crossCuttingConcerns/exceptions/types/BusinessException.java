package com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}