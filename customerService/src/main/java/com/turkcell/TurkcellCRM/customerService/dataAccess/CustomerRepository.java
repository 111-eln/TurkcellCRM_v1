package com.turkcell.TurkcellCRM.customerService.dataAccess;

import com.turkcell.TurkcellCRM.customerService.dtos.request.SearchCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.SearchCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreateCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Optional<Customer> findByNationalityNumber(String nationalityId);
}
