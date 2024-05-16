package com.turkcell.TurkcellCRM.orderService;

<<<<<<< HEAD
import com.turkcell.TurkcellCRM.commonPackage.Gender;
import com.turkcell.TurkcellCRM.orderService.business.concretes.OrderManager;
=======
import com.turkcell.TurkcellCRM.commonPackage.OrderCreatedEvent;
import com.turkcell.TurkcellCRM.orderService.business.concretes.OrderManager;
import com.turkcell.TurkcellCRM.orderService.business.rules.OrderBusinnesRules;
>>>>>>> eb17ef4439472449f7cac7365fe65111d38cf4ed
import com.turkcell.TurkcellCRM.orderService.core.exceptions.types.BusinessException;
import com.turkcell.TurkcellCRM.orderService.core.mapping.ModelMapperManager;
import com.turkcell.TurkcellCRM.orderService.core.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.orderService.dataAccess.OrderRepository;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.create.CreateOrderRequest;
<<<<<<< HEAD
=======
import com.turkcell.TurkcellCRM.orderService.dtos.requests.update.UpdateOrderRequest;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.create.CreateOrderResponse;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.get.GetOrderResponse;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.update.UpdateOrderResponse;
>>>>>>> eb17ef4439472449f7cac7365fe65111d38cf4ed
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Address;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Customer;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Order;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Product;
import com.turkcell.TurkcellCRM.orderService.kafka.producers.OrderProducer;
<<<<<<< HEAD
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
=======

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderManagerTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderProducer orderProducer;

    @InjectMocks
    private OrderManager orderManager;

    @BeforeEach
    void setUp(){
        orderRepository = Mockito.mock(OrderRepository.class);
        ModelMapper mapper = new ModelMapper();
        OrderBusinnesRules businnesRules = new OrderBusinnesRules(orderRepository);
        ModelMapperService modelMapperService = new ModelMapperManager(mapper);
        orderProducer=Mockito.mock(OrderProducer.class);
        orderManager = new OrderManager( modelMapperService,orderRepository,orderProducer,businnesRules);
    }

    @Test
    public void addOrderSuccess() {
        Order order =new Order();
        Product product1=new Product("test","test",12,12,order);
        Product product2=new Product("test","test",12,12,order);

        List<Product> products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        order.setAddress(new Address());
        order.setCustomer(new Customer());
        order.setProducts(products);

        order.setTotalAmount(12);
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(order);
        CreateOrderRequest request = new CreateOrderRequest(1,1,12,products);
        orderManager.add(request);
        assert true;
    }

    @Test
    public void getAllOrderSuccess() {
        Order order =new Order();
        Product product1=new Product("test","test",12,12,order);
        Product product2=new Product("test","test",12,12,order);
        List<Product> products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        order.setAddress(new Address());
        order.setCustomer(new Customer());
        order.setProducts(products);
        List<Order> orders = new ArrayList<Order>();
        orders.add(order);
        orders.add(order);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        orderManager.getAll();
        assert true;
    }
        @Test
    void updateOrderSuccess(){
        Order order =new Order();
        Product product1=new Product("test","test",12,12,order);
        Product product2=new Product("test","test",12,12,order);
        List<Product> products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        order.setAddress(new Address());
        order.setCustomer(new Customer());
        order.setProducts(products);
        Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(new Order()));
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(order);
        UpdateOrderRequest request = new UpdateOrderRequest(1,1,1,12,products);
        orderManager.update(request,1);
        assert true;
        }
    @Test
    void updateOrderNotExists_ShouldThrowException(){
        Order order =new Order();
        Product product1=new Product("test","test",12,12,order);
        Product product2=new Product("test","test",12,12,order);
        List<Product> products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        order.setAddress(new Address());
        order.setCustomer(new Customer());
        order.setProducts(products);
        UpdateOrderRequest request = new UpdateOrderRequest(1,1,1,12,products);
        assertThrows(BusinessException.class,()->{
            orderManager.update(request,2);
        });
    }
        @Test
    public void getByIdOrderSuccess() {
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(new Order()));
        orderManager.getById(1);
        assert true;
    }

}

>>>>>>> eb17ef4439472449f7cac7365fe65111d38cf4ed
