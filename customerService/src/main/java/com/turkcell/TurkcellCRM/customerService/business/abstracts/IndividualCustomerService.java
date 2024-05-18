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
<<<<<<< HEAD

=======
<<<<<<< HEAD
    void delete(int id, String authorizationHeader);
    GetIndividualCustomerResponse getById(int id, String authorizationHeader);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateCustomerRequest, int customerId, String authorizationHeader);
    List<GetAllIndividualCustomerResponse> getAll(String authorizationHeader);
=======
    boolean add2(Integer id);
>>>>>>> b5f0f69f24cdb44aaf1d6801236856d9c45b39fc
    void delete(int id);
    boolean getById2(int id);
    GetIndividualCustomerResponse getById(int id);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateCustomerRequest, int customerId);
    List<GetAllIndividualCustomerResponse> getAll();
>>>>>>> 48a990d87e30f6949dc9eca1f60f41f8b81b3753
}
