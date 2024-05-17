package com.turkcell.TurkcellCRM.customerService.business.concretes;


import com.turkcell.TurkcellCRM.customerService.business.abstracts.IndividualCustomerService;

import com.turkcell.TurkcellCRM.customerService.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.TurkcellCRM.customerService.core.crossCuttingConcerns.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.customerService.dataAccess.IndividualCustomerRepository;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.request.update.UpdateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.create.CreatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.commonPackage.CustomerCreatedEvent;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.dtos.response.update.UpdatedIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.IndividualCustomer;
import com.turkcell.TurkcellCRM.customerService.kafka.producers.IndividualCustomerProducer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private ModelMapperService modelMapperService;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private IndividualCustomerProducer individualCustomerProducer;

    @Transactional
    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {

        individualCustomerBusinessRules.customerAlreadyExists(createIndividualCustomerRequest.getNationalityNumber());
        individualCustomerBusinessRules.checkMernis(createIndividualCustomerRequest);

        IndividualCustomer customer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        customer.setCreatedDate(LocalDateTime.now());

        IndividualCustomer createdCustomer = individualCustomerRepository.save(customer);

        CustomerCreatedEvent customerCreatedEvent = modelMapperService.forResponse().map(createdCustomer,CustomerCreatedEvent.class);

        individualCustomerProducer.sendMessage(customerCreatedEvent);

        return modelMapperService.forResponse().map(createdCustomer, CreatedIndividualCustomerResponse.class);
    }

    @Transactional
    @Override
    public void delete(int id) {

        individualCustomerBusinessRules.customerShouldBeExists(id);

        IndividualCustomer currentCustomer = this.individualCustomerRepository.findById(id).get();

        currentCustomer.setDeleted(true);
        currentCustomer.setDeletedDate(LocalDateTime.now());
    }

    @Override
    public GetIndividualCustomerResponse getById(int id) {

        individualCustomerBusinessRules.customerShouldBeExists(id);

        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).get();
        GetIndividualCustomerResponse getCustomerResponse = modelMapperService.forResponse().map(individualCustomer, GetIndividualCustomerResponse.class);

        return getCustomerResponse;
    }

    @Transactional
    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateCustomerRequest, int customerId) {

        individualCustomerBusinessRules.customerShouldBeExists(customerId);
        individualCustomerBusinessRules.checkMernis(modelMapperService.forRequest().map(updateCustomerRequest,CreateIndividualCustomerRequest.class)); //TODO: Buraya bakılacak

        IndividualCustomer customerToUpdate = modelMapperService.forRequest().map(updateCustomerRequest, IndividualCustomer.class);

        customerToUpdate.setUpdatedDate(LocalDateTime.now());
        customerToUpdate.setId(customerId);

        IndividualCustomer updatedCustomer = individualCustomerRepository.save(customerToUpdate);

        return modelMapperService.forResponse().map(updatedCustomer, UpdatedIndividualCustomerResponse.class);
    }

    @Override
    public List<GetAllIndividualCustomerResponse> getAll() {

        this.individualCustomerBusinessRules.customersShouldBeExist();

        List<IndividualCustomer> customers = individualCustomerRepository.findAll();

        return customers.stream().map(customer ->
                modelMapperService.forResponse()
                        .map(customer, GetAllIndividualCustomerResponse.class)).toList();
    }
}
