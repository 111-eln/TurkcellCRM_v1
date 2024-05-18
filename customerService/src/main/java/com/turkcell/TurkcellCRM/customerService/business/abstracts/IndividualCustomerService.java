package com.turkcell.TurkcellCRM.customerService.business.abstracts;


import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest customer, String authorizationHeader);// HttpServletRequest request);
    void delete(int id, String authorizationHeader);
    GetIndividualCustomerResponse getById(int id, String authorizationHeader);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateCustomerRequest, int customerId, String authorizationHeader);
    List<GetAllIndividualCustomerResponse> getAll(String authorizationHeader);
}
