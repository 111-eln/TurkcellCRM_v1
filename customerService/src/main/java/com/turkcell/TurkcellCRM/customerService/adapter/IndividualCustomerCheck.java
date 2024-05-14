package com.turkcell.TurkcellCRM.customerService.adapter;

import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.IndividualCustomer;

public interface IndividualCustomerCheck {
    public boolean checkIsRealPerson(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
