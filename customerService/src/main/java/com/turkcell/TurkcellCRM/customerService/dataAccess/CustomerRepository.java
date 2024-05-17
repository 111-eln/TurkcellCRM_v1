package com.turkcell.TurkcellCRM.customerService.dataAccess;

//import com.turkcell.TurkcellCRM.customerService.dtos.request.SearchCustomerRequest;
//import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateCustomerRequest;
//import com.turkcell.TurkcellCRM.customerService.dtos.response.SearchCustomerResponse;
//import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreateCustomerResponse;
//import com.turkcell.TurkcellCRM.customerService.entities.concretes.Customer;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.BaseCustomer;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<BaseCustomer, Integer> {


    Optional<BaseCustomer> findByNationalityNumber(String nationalityId);
}
