package com.turkcell.TurkcellCRM.customerService.business.abstracts;


import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdatedIndividualCustomerrResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest customer);
    void delete(int id);
    GetIndividualCustomerResponse getById(int id);
    UpdatedIndividualCustomerrResponse update(UpdateIndividualCustomerRequest updateCustomerRequest, int customerId);
    List<GetAllIndividualCustomerResponse> getAll();
}
