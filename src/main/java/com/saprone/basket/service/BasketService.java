package com.saprone.basket.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saprone.basket.model.Basket;
import com.saprone.basket.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public void addIngredientsToBasket(String encodedBasket) throws Exception {
        String decodedBasket = URLDecoder.decode(encodedBasket, StandardCharsets.UTF_8);
        List<Basket> baskets = objectMapper.readValue(decodedBasket.substring(0, decodedBasket.length() - 1), new TypeReference<List<Basket>>() {});
        basketRepository.saveAll(baskets);
    }

    public List<Basket> getBasket() {
        return basketRepository.findAll();
    }
}
