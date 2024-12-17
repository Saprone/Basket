package com.saprone.basket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saprone.basket.model.Basket;
import com.saprone.basket.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BasketControllerTest {

    @Mock
    private BasketService basketService;

    @InjectMocks
    private BasketController basketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addIngredientToBasketShouldReturnOk() throws JsonProcessingException {
        // Arrange
        String ingredient = "{\"id\":1,\"name\":\"Butter\"}"; // Example JSON string
        doNothing().when(basketService).addIngredientToBasket(anyString());

        // Act
        ResponseEntity<Void> response = basketController.addIngredientToBasket(ingredient);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        verify(basketService, times(1)).addIngredientToBasket(ingredient);
    }

    @Test
    void removeIngredientFromBasketShouldReturnOk() {
        // Arrange
        String ingredientId = "1"; // Example ID

        // Act
        ResponseEntity<Void> response = basketController.removeIngredientFromBasket(ingredientId);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        verify(basketService, times(1)).removeIngredientFromBasket(ingredientId);
    }

    @Test
    void getBasketShouldReturnBaskets() {
        // Arrange
        Basket basket = new Basket();
        basket.setId(1L);
        basket.setName("Basket1");
        when(basketService.getBasket()).thenReturn(List.of(basket));

        // Act
        ResponseEntity<List<Basket>> response = basketController.getBasket();

        // Assert
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody()).containsExactly(basket);
        verify(basketService, times(1)).getBasket();
    }

    @Test
    void addIngredientToBasketShouldReturnBadRequestOnException() throws JsonProcessingException {
        // Arrange
        String ingredient = "{\"id\":1,\"name\":\"Butter\"}"; // Example JSON string
        doThrow(new RuntimeException()).when(basketService).addIngredientToBasket(anyString());

        // Act
        ResponseEntity<Void> response = basketController.addIngredientToBasket(ingredient);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
        verify(basketService, times(1)).addIngredientToBasket(ingredient);
    }

    @Test
    void removeIngredientFromBasketShouldReturnBadRequestOnException() {
        // Arrange
        String ingredientId = "1"; // Example ID
        doThrow(new RuntimeException()).when(basketService).removeIngredientFromBasket(anyString());

        // Act
        ResponseEntity<Void> response = basketController.removeIngredientFromBasket(ingredientId);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(400);
        verify(basketService, times(1)).removeIngredientFromBasket(ingredientId);
    }
}
