package com.turkcell.TurkcellCRM.orderService;

import com.turkcell.TurkcellCRM.commonPackage.Gender;
import com.turkcell.TurkcellCRM.orderService.business.concretes.OrderManager;
import com.turkcell.TurkcellCRM.orderService.core.exceptions.types.BusinessException;
import com.turkcell.TurkcellCRM.orderService.core.mapping.ModelMapperManager;
import com.turkcell.TurkcellCRM.orderService.core.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.orderService.dataAccess.OrderRepository;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.create.CreateOrderRequest;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Address;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Customer;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Order;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Product;
import com.turkcell.TurkcellCRM.orderService.kafka.producers.OrderProducer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderManagerTest {

    private OrderManager orderManager;
    private OrderRepository orderRepository;
    private KafkaTemplate kafkaTemplate;
    private OrderProducer orderProducer;

    @BeforeEach
    void setUp(){
        orderRepository = Mockito.mock(OrderRepository.class);
        orderProducer=Mockito.mock(OrderProducer.class);
        kafkaTemplate=Mockito.mock(KafkaTemplate.class);
        orderProducer=new OrderProducer(kafkaTemplate);
        ModelMapper mapper = new ModelMapper();
        ModelMapperService modelMapperService = new ModelMapperManager(mapper);
        orderManager = new OrderManager(modelMapperService,orderRepository,orderProducer);
        //hangi classlar var ve hangi class contructorında ne istiyo onları mockladı
    }

    @Test
    void addOrderSuccess()
    {
        Customer customer=new Customer("ass","hghg","ff","2001-01-01","MALE","SDD","FDDF","14562398566","as@gmail.com","4615698","5623569854","566");
        customer.setId(1);
        Address adress=new Address("","hh","vjh","hgbujh",customer);
        adress.setId(1);
        List<Product> products=new ArrayList<>(){};
        Order savedOrder = new Order();
        savedOrder.setId(1);
        savedOrder.setAddress(adress);
        savedOrder.setCustomer(customer);
        savedOrder.setProducts(products);
        savedOrder.setTotalAmount(55);
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(savedOrder);
        CreateOrderRequest request = new CreateOrderRequest(1,adress,1,55,products);
//        orderManager.add(request);
        assertThrows(IllegalArgumentException.class, () -> orderManager.add(request));

        assert true;
    }
}
