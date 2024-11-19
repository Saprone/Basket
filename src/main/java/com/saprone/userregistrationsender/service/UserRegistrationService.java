package com.saprone.userregistrationsender.service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saprone.userregistrationsender.model.UserRegistration;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final ServiceBusSenderClient senderClient;

    public UserRegistrationService(ServiceBusSenderClient senderClient) {
        this.senderClient = senderClient;
    }

    public void registerUser(UserRegistration userRegistration) throws JsonProcessingException {
        String messageBody = new ObjectMapper().writeValueAsString(userRegistration);
        senderClient.sendMessage(new ServiceBusMessage(messageBody));
        System.out.printf("Sent a registration message: %s%n", messageBody);
    }
}
