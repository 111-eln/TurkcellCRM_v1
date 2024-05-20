package om.turkcell.TurkcellCRM.productService.business.dtos.requests.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductsRequest {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private int price;
    @NotNull
    private int unitOfStock;
}
