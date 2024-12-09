package com.saprone.basket.controller;

import com.saprone.basket.model.Basket;
import com.saprone.basket.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:8085")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/ingredient")
    public ResponseEntity<Void> addIngredientToBasket(@RequestBody String encodedBasket) {
        try {
            basketService.addIngredientsToBasket(encodedBasket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<Basket>> getBasket() {
        List<Basket> baskets = basketService.getBasket();
        return ResponseEntity.ok(baskets);
    }
}
