package com.turkcell.TurkcellCRM.customerService.kafka.producers;

import com.turkcell.TurkcellCRM.commonPackage.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndividualCustomerProducer {
    //private static final Logger LOGGER = LoggerFactory.getLogger(IndividualCustomerProducer.class);
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public IndividualCustomerProducer(KafkaTemplate<String,Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(CustomerCreatedEvent customerCreatedEvent){
        log.info(String.format("Customer added =>%s",customerCreatedEvent.toString()));

        Message<CustomerCreatedEvent> message = MessageBuilder.withPayload(customerCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, "inventory-customer-created")
                .build();
        kafkaTemplate.send(message);
    }
}