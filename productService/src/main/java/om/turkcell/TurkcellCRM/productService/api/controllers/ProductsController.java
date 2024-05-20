package om.turkcell.TurkcellCRM.productService.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import om.turkcell.TurkcellCRM.productService.business.abstracts.ProductService;
import om.turkcell.TurkcellCRM.productService.business.dtos.requests.create.CreateProductsRequest;
import om.turkcell.TurkcellCRM.productService.business.dtos.responses.create.CreatedProductResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("productservice/api/v1/products")
public class ProductsController {
    private final ProductService productService;

    @PostMapping
    public CreatedProductResponse add(@Valid @RequestBody CreateProductsRequest createProductsRequest){
        return this.productService.add(createProductsRequest);
    }
}
