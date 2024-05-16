package com.turkcell.TurkcellCRM.orderService.entities.concretes;

import com.turkcell.TurkcellCRM.commonPackage.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {
    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "street")
    @NotNull
    @Size(min=3,max=16)
    private String street;

    @Column(name = "houseNumber")
    @NotNull
    private String houseNumber;

    @Column(name = "addressDescription")
    @NotNull
    private String addressDescription;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
