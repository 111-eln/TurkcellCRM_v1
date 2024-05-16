package com.turkcell.TurkcellCRM.customerService.business.concretes;

import com.turkcell.TurkcellCRM.customerService.adapter.MernisService;
import com.turkcell.TurkcellCRM.customerService.business.rules.IndividualCustomerBusinessRules;
import com.turkcell.TurkcellCRM.customerService.core.crossCuttingConcerns.exceptions.types.BusinessException;
import com.turkcell.TurkcellCRM.customerService.core.crossCuttingConcerns.mapping.ModelMapperManager;
import com.turkcell.TurkcellCRM.customerService.core.crossCuttingConcerns.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.customerService.dataAccess.IndividualCustomerRepository;
import com.turkcell.TurkcellCRM.customerService.dtos.request.create.CreateIndividualCustomerRequest;
import com.turkcell.TurkcellCRM.customerService.dtos.response.get.GetAllIndividualCustomerResponse;
import com.turkcell.TurkcellCRM.customerService.entities.concretes.IndividualCustomer;
import com.turkcell.TurkcellCRM.customerService.entities.enums.Gender;
import com.turkcell.TurkcellCRM.customerService.kafka.producers.IndividualCustomerProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndividualCustomerManagerTest {
    private IndividualCustomerManager individualCustomerManager;
    private IndividualCustomerRepository individualCustomerRepository;
    private KafkaTemplate<String, Object> kafkaTemplate;
    private ModelMapper modelMapper;
    private MernisService mernisService;
    private IndividualCustomerBusinessRules rules;


    @BeforeEach
    void setUp(){
        individualCustomerRepository = mock(IndividualCustomerRepository.class);
        modelMapper = new ModelMapper();
        ModelMapperService modelMapperService = new ModelMapperManager(modelMapper);
        mernisService = mock(MernisService.class);
        rules = new IndividualCustomerBusinessRules(individualCustomerRepository, mernisService);
        kafkaTemplate = mock(KafkaTemplate.class);
        IndividualCustomerProducer producer = new IndividualCustomerProducer(kafkaTemplate);
        individualCustomerManager = new IndividualCustomerManager(individualCustomerRepository,modelMapperService,rules,producer);
    }


    @Test
    void deleteById(){
        when(individualCustomerRepository.findById(1)).thenReturn(Optional.of(new IndividualCustomer()));
        individualCustomerManager.delete(1);
        assert true;
    }

    @Test
    void deleteWithNotExistsId_ShouldThrowException(){
        when(individualCustomerRepository.findById(1)).thenReturn(Optional.empty());
        //individualCustomerManager.delete(1);

        assertThrows(BusinessException.class, () -> {
            individualCustomerManager.delete(1);
        });
    }

    @Test
    void getById(){
        when(individualCustomerRepository.findById(1)).thenReturn(Optional.of(new IndividualCustomer()));
        individualCustomerManager.getById(1);
        assert true;
    }

    @Test
    void getByIdWithNotExistsId_ShouldThrowException(){
        when(individualCustomerRepository.findById(1)).thenReturn(Optional.empty());
        //individualCustomerManager.getById(1);

        assertThrows(BusinessException.class, () -> {
            individualCustomerManager.getById(1);
        });

    }

    @Test
    void getAll(){
        IndividualCustomer customer1 = new IndividualCustomer();
        IndividualCustomer customer2 = new IndividualCustomer();
        List<IndividualCustomer> list = new ArrayList<>();

        list.add(customer1);
        list.add(customer2);
        when(individualCustomerRepository.findAll()).thenReturn(list);

        // Call the getAll method and capture the result
        List<GetAllIndividualCustomerResponse> result = individualCustomerManager.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void getAllShouldThrowException(){
        when(individualCustomerRepository.findAll()).thenReturn(new ArrayList<IndividualCustomer>());
        /*List<GetAllIndividualCustomerResponse> result = individualCustomerManager.getAll();
        assertEquals(0, result.size());*/

        assertThrows(BusinessException.class, () -> {
            individualCustomerManager.getAll();
        });
    }


    //TODO: add için yazılan testlerdeki ortak alanları fonksiyona çevir ve o fonksiyonları çağır.
    @Test
    void add(){
        IndividualCustomer customer = new IndividualCustomer();

        customer.setBirthDate(LocalDateTime.now());
        customer.setNationalityNumber("12345678902");
        customer.setFirstName("Deneme");
        customer.setLastName("Deneme");
        customer.setGender(Gender.MALE);
        customer.setFatherName("Deneme");
        customer.setMotherName("Deneme");

        CreateIndividualCustomerRequest request = new CreateIndividualCustomerRequest();

        request.setBirthDate(customer.getBirthDate());
        request.setFirstName(customer.getFirstName());
        request.setSecondName("");
        request.setLastName(customer.getLastName());
        request.setNationalityNumber(customer.getNationalityNumber());
        request.setGender(com.turkcell.TurkcellCRM.commonPackage.Gender.MALE);
        request.setFatherName(customer.getFatherName());
        request.setMotherName(customer.getMotherName());

        when(individualCustomerRepository.findByNationalityNumber("98765432102")).thenReturn(Optional.empty());

        when(individualCustomerRepository.save(customer)).thenReturn(new IndividualCustomer());


        when(mernisService.checkIsRealPerson(request)).thenReturn(true);

        individualCustomerManager.add(request);
    }

    @Test
    void addShouldThrowExceptionForIndividualCustomerExists(){

        IndividualCustomer customer = new IndividualCustomer();

        customer.setBirthDate(LocalDateTime.now());
        customer.setNationalityNumber("12345678902");
        customer.setFirstName("Deneme");
        customer.setLastName("Deneme");
        customer.setGender(Gender.MALE);
        customer.setFatherName("Deneme");
        customer.setMotherName("Deneme");

        CreateIndividualCustomerRequest request = new CreateIndividualCustomerRequest();

        request.setBirthDate(customer.getBirthDate());
        request.setFirstName(customer.getFirstName());
        request.setSecondName("");
        request.setLastName(customer.getLastName());
        request.setNationalityNumber(customer.getNationalityNumber());
        request.setGender(com.turkcell.TurkcellCRM.commonPackage.Gender.MALE);
        request.setFatherName(customer.getFatherName());
        request.setMotherName(customer.getMotherName());

        when(individualCustomerRepository.findByNationalityNumber("12345678902")).thenReturn(Optional.of(new IndividualCustomer()));
        when(individualCustomerRepository.save(customer)).thenReturn(new IndividualCustomer());

        assertThrows(BusinessException.class, () -> {
            individualCustomerManager.add(request);
        });

    }

    @Test
    void addShouldThrowExceptionForCheckMernis(){

        IndividualCustomer customer = new IndividualCustomer();

        customer.setBirthDate(LocalDateTime.now());
        customer.setNationalityNumber("12345678902");
        customer.setFirstName("Deneme");
        customer.setLastName("Deneme");
        customer.setGender(Gender.MALE);
        customer.setFatherName("Deneme");
        customer.setMotherName("Deneme");

        CreateIndividualCustomerRequest request = new CreateIndividualCustomerRequest();

        request.setBirthDate(customer.getBirthDate());
        request.setFirstName(customer.getFirstName());
        request.setSecondName("");
        request.setLastName(customer.getLastName());
        request.setNationalityNumber(customer.getNationalityNumber());
        request.setGender(com.turkcell.TurkcellCRM.commonPackage.Gender.MALE);
        request.setFatherName(customer.getFatherName());
        request.setMotherName(customer.getMotherName());

        when(individualCustomerRepository.findByNationalityNumber("98765432102")).thenReturn(Optional.empty());

        when(mernisService.checkIsRealPerson(request)).thenReturn(false);

        assertThrows(BusinessException.class, () -> {
            individualCustomerManager.add(request);
        });
    }

    // TODO: update için gerekli test fonksiyonlarını yaz
}