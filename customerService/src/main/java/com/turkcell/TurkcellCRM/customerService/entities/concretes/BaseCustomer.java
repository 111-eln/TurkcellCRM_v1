package com.turkcell.TurkcellCRM.customerService.entities.concretes;
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
@Table(name = "customers")
public class BaseCustomer extends BaseEntity {

    @OneToOne(mappedBy = "customer")
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToOne
    @JoinColumn(name = "individualId", referencedColumnName = "id")
    private IndividualCustomer individualCustomer;
}
