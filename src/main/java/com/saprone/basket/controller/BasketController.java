package com.saprone.basket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saprone.basket.model.Basket;
import com.saprone.basket.service.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ResponseEntity<Void> sendBasket(@RequestBody Basket Basket) throws JsonProcessingException {
        basketService.sendBasket(Basket);

        return ResponseEntity.accepted().build();
    }
}
