package com.turkcell.TurkcellCRM.orderService.dtos.requests.create;

import com.turkcell.TurkcellCRM.orderService.entities.concretes.Address;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequest {
//    @NotNull
//    private int accountId;
//    @NotNull
//    private int addressId;
//    @jakarta.validation.constraints.NotNull
//    private Address address;
    @NotNull
    private int customerId;
    @NotNull
    private int totalAmount;
//    @NotNull
//    private List<Product> products;

}
