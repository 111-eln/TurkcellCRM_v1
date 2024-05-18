package com.turkcell.TurkcellCRM.customerService.api.controllers;

import com.turkcell.TurkcellCRM.customerService.business.abstracts.IndividualCustomerService;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse update(@Valid @RequestBody UpdateIndividualCustomerRequest updateCustomerRequest, @PathVariable int customerId,@RequestHeader("Authorization") String request) {
        return customerService.update(updateCustomerRequest,customerId,request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getById(@PathVariable int id,@RequestHeader("Authorization") String request) {
        return customerService.getById(id, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllIndividualCustomerResponse> getAll(@RequestHeader("Authorization") String request) {
        return customerService.getAll(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id ,@RequestHeader("Authorization") String request){
        customerService.delete(id,request);
    }
}