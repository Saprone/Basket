package com.saprone.basket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:8085")
public class BasketController {

    @PostMapping("/ingredient")
    public ResponseEntity<Void> addIngredientToBasket(@RequestBody String encodedBasket) {
        String decodedBasket = URLDecoder.decode(encodedBasket, StandardCharsets.UTF_8);
        System.out.println(decodedBasket); //[{"id":97,"name":"Dark Brown Sugar"},{"id":117,"name":"Farfalle"}]=

        return null;
    }
}
