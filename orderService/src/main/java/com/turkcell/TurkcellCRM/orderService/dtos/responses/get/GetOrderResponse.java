package com.turkcell.TurkcellCRM.orderService.dtos.responses.get;

import com.turkcell.TurkcellCRM.orderService.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetOrderResponse {
    @NotNull
    private List<Product> products;
}
