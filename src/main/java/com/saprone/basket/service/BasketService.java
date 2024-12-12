package com.saprone.basket.service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saprone.basket.model.Basket;
import com.saprone.basket.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ServiceBusSenderClient senderClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BasketService(BasketRepository basketRepository, ServiceBusSenderClient senderClient) {
        this.basketRepository = basketRepository;
        this.senderClient = senderClient;
    }

    public void addIngredientToBasket(String ingredientEncoded) throws Exception {
        String ingredientDecoded = URLDecoder.decode(ingredientEncoded, StandardCharsets.UTF_8);
        Basket basket = objectMapper.readValue(ingredientDecoded.substring(0, ingredientDecoded.length() - 1), new TypeReference<Basket>() {});
        basketRepository.save(basket);
    }

    public void removeIngredientFromBasket(String ingredientId) {
        basketRepository.deleteById(Integer.valueOf(ingredientId.substring(0, ingredientId.length() - 1)));
    }

    public void sendBasketToMessageQueue(List<Basket> baskets) throws JsonProcessingException {
        List<Object> objectBaskets = new ArrayList<>(baskets);
        String messageBody = new ObjectMapper().writeValueAsString(objectBaskets);
        senderClient.sendMessage(new ServiceBusMessage(messageBody));
    }

    public List<Basket> getBasket() {
        return basketRepository.findAll();
    }
}
