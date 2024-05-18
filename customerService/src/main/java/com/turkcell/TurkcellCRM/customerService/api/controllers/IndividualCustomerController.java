package com.turkcell.TurkcellCRM.customerService.api.controllers;

import com.turkcell.TurkcellCRM.customerService.business.abstracts.IndividualCustomerService;
import com.turkcell.TurkcellCRM.customerService.dataAccess.IndividualCustomerRepository;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.IndividualCustomer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/individualcustomerservice/api/v1/customers")
public class IndividualCustomerController {
    private IndividualCustomerService customerService;
//    private final IndividualCustomerRepository individualCustomerRepository;

//    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest createCustomerRequest,
                                                 @RequestHeader("Authorization") String request) {

        return customerService.add(createCustomerRequest,request);
    }
    //todo: repo olarak kalmamalı
    @PostMapping("/getCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean add2(@RequestBody Integer id) {
//        IndividualCustomer individualCustomer=individualCustomerRepository.findById(id).get();
//        return !individualCustomer.equals(null);
        return customerService.add2(id);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse update(@Valid @RequestBody UpdateIndividualCustomerRequest updateCustomerRequest, @PathVariable int customerId) {
        return customerService.update(updateCustomerRequest,customerId);
    }

    @GetMapping("/{id}")
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