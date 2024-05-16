package com.turkcell.TurkcellCRM.customerService.api.controllers;

import com.turkcell.TurkcellCRM.customerService.business.abstracts.IndividualCustomerService;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdatedIndividualCustomerrResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customerservice/api/v1/customers")
public class IndividualCustomerController {
    private IndividualCustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        return customerService.add(createIndividualCustomerRequest);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerrResponse update(@Valid @RequestBody UpdateIndividualCustomerRequest updateCustomerRequest, @PathVariable int customerId) {
        return customerService.update(updateCustomerRequest,customerId);
    }

    @GetMapping("/{id}")  //TODO: getById şeklinde bir tanımlama eklenmeli mi
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllIndividualCustomerResponse> getAll() {
        return customerService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id ){
        customerService.delete(id);
    }
}