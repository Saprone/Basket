package com.saprone.basket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:8085")
public class BasketController {

    @PostMapping("/post")
    public ResponseEntity<String> postData(@RequestBody String encodedBasket) {
        String decodedBasket = URLDecoder.decode(encodedBasket, StandardCharsets.UTF_8);

        return ResponseEntity.ok("Post request succeeded with data: " + decodedBasket);
    }
}
