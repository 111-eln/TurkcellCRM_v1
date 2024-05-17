package com.turkcell.TurkcellCRM.orderService.entities.concretes;

import com.turkcell.TurkcellCRM.commonPackage.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    private Account account;

//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address address;
//@ManyToOne
//@JoinColumn(name = "address_id")
    @Column(name = "addressId")
    private int addressId;
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
    @Column(name="customerId")
    private int customerId;

    @Column(name = "totalAmount")
    private int totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;
}
