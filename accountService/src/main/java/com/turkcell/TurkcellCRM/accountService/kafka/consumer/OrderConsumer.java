package com.turkcell.TurkcellCRM.accountService.kafka.consumer;

import com.turkcell.TurkcellCRM.accountService.business.abstracts.AccountService;
import com.turkcell.TurkcellCRM.accountService.core.Order;
import com.turkcell.TurkcellCRM.commonPackage.IndividualCustomerCreatedEvent;
import com.turkcell.TurkcellCRM.commonPackage.OrderCreatedForAccountEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
    private final AccountService accountService;

    @KafkaListener(topics = "account-order-created",groupId="accountorder-create")
    public void consume(OrderCreatedForAccountEvent orderCreatedForAccountEvent){

        Order order = new Order();
        order.setId(orderCreatedForAccountEvent.getOrderId());
        order.setAccountNumber(orderCreatedForAccountEvent.getAccountNumber());

        LOGGER.info(String.format("Account-Order consumed =>%s",order.toString()));

        this.accountService.addOrderToAccount(order);
    }
}
