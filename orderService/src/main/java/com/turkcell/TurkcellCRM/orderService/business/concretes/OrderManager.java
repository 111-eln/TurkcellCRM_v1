package com.turkcell.TurkcellCRM.orderService.business.concretes;

import com.turkcell.TurkcellCRM.commonPackage.OrderCreatedEvent;
import com.turkcell.TurkcellCRM.orderService.business.abstracts.OrderService;
import com.turkcell.TurkcellCRM.orderService.business.rules.OrderBusinnesRules;
import com.turkcell.TurkcellCRM.orderService.core.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.orderService.dataAccess.OrderRepository;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.create.CreateOrderRequest;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.update.UpdateOrderRequest;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.create.CreateOrderResponse;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.get.GetOrderResponse;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.update.UpdateOrderResponse;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Order;
import com.turkcell.TurkcellCRM.orderService.kafka.producers.OrderProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private ModelMapperService modelMapperService;
    private OrderRepository orderRepository;
    private OrderProducer orderProducer;
    private OrderBusinnesRules orderBusinnesRules;
    @Override
    public CreateOrderResponse add(CreateOrderRequest orderRequest) {
        Order order=modelMapperService.forRequest().map(orderRequest, Order.class);
        Order dbOrder=orderRepository.save(order);
        OrderCreatedEvent orderCreatedEvent=modelMapperService.forResponse().map(dbOrder,OrderCreatedEvent.class);
        orderProducer.sendMessage(orderCreatedEvent);
        return modelMapperService.forResponse().map(dbOrder, CreateOrderResponse.class);
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public GetOrderResponse getById(int id) {
        Order order=orderRepository.findById(id).orElse(null);

        return modelMapperService.forResponse().map(order, GetOrderResponse.class) ;
    }

    @Override
    public UpdateOrderResponse update(UpdateOrderRequest updateOrderRequest, int orderId) {
       orderBusinnesRules.orderShouldBeExists(orderId);

        Order order=orderRepository.findById(orderId).orElse(null);
        order.setId(orderId);
        Order dbOrder=orderRepository.save(order);
        return modelMapperService.forResponse().map(dbOrder,UpdateOrderResponse.class);
    }

    @Override
    public List<GetOrderResponse> getAll() {
        List<Order> orders=orderRepository.findAll();

        return  orders.stream().map(order->  modelMapperService.forResponse().map(order, GetOrderResponse.class)).toList();


    }
}
