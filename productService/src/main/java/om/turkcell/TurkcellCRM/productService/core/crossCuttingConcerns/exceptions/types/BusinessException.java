package om.turkcell.TurkcellCRM.productService.crossCuttingConcerns.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}