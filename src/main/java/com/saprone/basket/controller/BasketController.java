package com.saprone.basket.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saprone.basket.model.Basket;
import com.saprone.basket.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:8085")
public class BasketController {

    private final BasketRepository basketRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @PostMapping("/ingredient")
    public ResponseEntity<Void> addIngredientToBasket(@RequestBody String encodedBasket) {
        String decodedBasket = URLDecoder.decode(encodedBasket, StandardCharsets.UTF_8);

        try {
            List<Basket> baskets = objectMapper.readValue(decodedBasket.substring(0, decodedBasket.length() - 1), new TypeReference<List<Basket>>() {});

            basketRepository.saveAll(baskets);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
