package com.turkcell.TurkcellCRM.orderService.business.concretes;

import com.turkcell.TurkcellCRM.commonPackage.OrderCreatedEvent;
import com.turkcell.TurkcellCRM.commonPackage.OrderCreatedForAccountEvent;
import com.turkcell.TurkcellCRM.orderService.business.abstracts.OrderService;
import com.turkcell.TurkcellCRM.orderService.business.rules.OrderBusinnesRules;
import com.turkcell.TurkcellCRM.orderService.clients.IsCustomerExistClient;
import com.turkcell.TurkcellCRM.orderService.clients.ProductStockIsEnoughClient;
import com.turkcell.TurkcellCRM.orderService.clients.ProductTitleIsExistClient;
import com.turkcell.TurkcellCRM.orderService.core.exceptions.types.BusinessException;
import com.turkcell.TurkcellCRM.orderService.core.mapping.ModelMapperService;
import com.turkcell.TurkcellCRM.orderService.dataAccess.OrderRepository;
import com.turkcell.TurkcellCRM.orderService.dataAccess.ProductRepository;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.create.CreateOrderRequest;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.create.CreateProductRequest;
import com.turkcell.TurkcellCRM.orderService.dtos.requests.update.UpdateOrderRequest;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.create.CreateOrderResponse;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.get.GetOrderResponse;
import com.turkcell.TurkcellCRM.orderService.dtos.responses.update.UpdateOrderResponse;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Order;
import com.turkcell.TurkcellCRM.orderService.entities.concretes.Product;
import com.turkcell.TurkcellCRM.orderService.kafka.producers.OrderForAccountProducer;
import com.turkcell.TurkcellCRM.orderService.kafka.producers.OrderProducer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private ModelMapperService modelMapperService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private IsCustomerExistClient isCustomerExistClient;
    private OrderProducer orderProducer;
    private OrderForAccountProducer orderForAccountProducer;
    private OrderBusinnesRules orderBusinnesRules;
    private ProductStockIsEnoughClient productStockIsEnoughClient;
    private ProductTitleIsExistClient productTitleIsExistClient;



    @Transactional
    @Override
    public CreateOrderResponse add(CreateOrderRequest orderRequest) {
        if(!this.isCustomerExistClient.getById2(orderRequest.getCustomerId())){
            throw new BusinessException("Customer bulunamadi");
        }

        Order order=modelMapperService.forRequest().map(orderRequest, Order.class);
        Order dbOrder=orderRepository.save(order);

        //------------
        List<CreateProductRequest> products=orderRequest.getProducts();
        List<Product> productsNew=products.stream().map(product->  modelMapperService.forResponse().map(product, Product.class)).toList();
        for (Product product:productsNew) {
            if(!productTitleIsExistClient.controlProductTitle(product.getTitle())){
                throw new BusinessException("Bu isimde bir urun bulunamadi");
            }
            if(!productStockIsEnoughClient.controlProductStock(product.getTitle())){
                throw new BusinessException("Urun stok yetersiz");
            }

            product.setOrderId(dbOrder.getId());
            productRepository.save(product);
        }
        //----------------
        OrderCreatedEvent orderCreatedEvent=modelMapperService.forResponse().map(dbOrder,OrderCreatedEvent.class);
        orderProducer.sendMessage(orderCreatedEvent);
        OrderCreatedForAccountEvent orderCreatedForAccountEvent=modelMapperService.forResponse().map(dbOrder,OrderCreatedForAccountEvent.class);
        orderCreatedForAccountEvent.setOrderId(dbOrder.getId());
        orderForAccountProducer.sendMessage(orderCreatedForAccountEvent);
        return modelMapperService.forResponse().map(dbOrder, CreateOrderResponse.class);
    }

    @Transactional
    @Override
    public void delete(int id) {

        orderBusinnesRules.orderShouldBeExists(id);

        Order currentOrder = this.orderRepository.findById(id).get();
        currentOrder.setDeleted(true);
        currentOrder.setDeletedDate(LocalDateTime.now());
    }

    @Override
    public GetOrderResponse getById(int id) {

        orderBusinnesRules.orderShouldBeExists(id);

        Order order = orderRepository.findById(id).get();
        return modelMapperService.forResponse().map(order, GetOrderResponse.class) ;
    }

    @Transactional
    @Override
    public UpdateOrderResponse update(UpdateOrderRequest updateOrderRequest, int orderId) {

       orderBusinnesRules.orderShouldBeExists(orderId);
       //todo:alreadyexist business rules yazılcak

        Order order = orderRepository.findById(orderId).get();
        order.setId(orderId);
        order.setUpdatedDate(LocalDateTime.now());
        Order dbOrder=orderRepository.save(order);
        return modelMapperService.forResponse().map(dbOrder,UpdateOrderResponse.class);
    }

    @Override
    public List<GetOrderResponse> getAll() {

        orderBusinnesRules.ordersShouldBeExist();

        List<Order> orders=orderRepository.findAll();

        return  orders.stream().map(order->  modelMapperService.forResponse().map(order, GetOrderResponse.class)).toList();
    }
}
