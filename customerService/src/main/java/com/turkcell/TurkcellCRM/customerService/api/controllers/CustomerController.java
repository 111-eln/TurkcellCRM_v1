package com.turkcell.TurkcellCRM.customerService.api.controllers;

import com.turkcell.TurkcellCRM.customerService.business.abstracts.CustomerService;
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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customerservice/api/v1/customers")

//@RequestMapping("/customerservice/customers")
public class CustomerController {
    private CustomerService customerService;
    @GetMapping("search")
    public List<SearchCustomerResponse> search()
    {
        return customerService.search();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody CreateUserJwtRequest userInfo) {
        return customerService.getJwt(userInfo);
    }
    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest,
                                      @RequestHeader("Authorization") String request) {

        return customerService.add(createCustomerRequest,request);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCustomerResponse update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest, @PathVariable int customerId) {
        return customerService.update(updateCustomerRequest,customerId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCustomerResponse> getAll() {
        return customerService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id ){
        customerService.delete(id);
    }
}