package com.saprone.basket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:8085")
public class BasketController {

    @PostMapping("/post")
    public ResponseEntity<String> postData(@RequestBody String encodedBasket) throws JsonProcessingException {
        String decodedBasket = URLDecoder.decode(encodedBasket, StandardCharsets.UTF_8);
        JsonNode jsonNode = new ObjectMapper().readTree(decodedBasket);

        jsonNode.forEach(System.out::println);

        return ResponseEntity.ok("Post request succeeded with data: " + decodedBasket);
    }
}
