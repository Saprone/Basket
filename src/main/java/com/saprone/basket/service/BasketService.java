package com.saprone.basket.service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saprone.basket.model.Basket;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final ServiceBusSenderClient senderClient;

    public BasketService(ServiceBusSenderClient senderClient) {
        this.senderClient = senderClient;
    }

    public void sendBasket(Basket basket) throws JsonProcessingException {
        String messageBody = new ObjectMapper().writeValueAsString(basket);
        senderClient.sendMessage(new ServiceBusMessage(messageBody));

        System.out.printf("Sent a basket message: %s%n", messageBody);
    }
}
