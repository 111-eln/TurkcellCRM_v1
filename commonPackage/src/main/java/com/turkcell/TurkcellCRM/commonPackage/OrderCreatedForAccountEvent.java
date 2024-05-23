package com.turkcell.TurkcellCRM.commonPackage;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedForAccountEvent {
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "accountNumber")
    private int accountNumber;



}
