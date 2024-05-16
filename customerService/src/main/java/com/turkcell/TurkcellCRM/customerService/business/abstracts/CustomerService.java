package com.turkcell.TurkcellCRM.customerService.business.abstracts;


import com.turkcell.TurkcellCRM.customerService.dtos.request.CreateUserJwtRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.SearchCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.SearchCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreateCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdateCustomerResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface CustomerService {
    //todo response ve request yaz
    List<SearchCustomerResponse> search();
    String getJwt(CreateUserJwtRequest userInfo);
    CreateCustomerResponse add(CreateCustomerRequest customer, String authorizationHeader);// HttpServletRequest request);
    void delete(int id);
    GetCustomerResponse getById(int id);
    UpdateCustomerResponse update(UpdateCustomerRequest updateCustomerRequest, int customerId);
    List<GetAllCustomerResponse> getAll();
}
