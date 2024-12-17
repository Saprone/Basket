package com.saprone.basket.service;

import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.saprone.basket.model.Basket;
import com.saprone.basket.repository.BasketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    @Mock
    private BasketRepository basketRepository;

    @Mock
    private ServiceBusSenderClient senderClient;

    @InjectMocks
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBasketShouldReturnListOfBaskets() {
        // Arrange
        Basket basket1 = new Basket();
        basket1.setId(1L);
        basket1.setName("Basket1");

        Basket basket2 = new Basket();
        basket2.setId(2L);
        basket2.setName("Basket2");

        when(basketRepository.findAll()).thenReturn(List.of(basket1, basket2));

        // Act
        List<Basket> baskets = basketService.getBasket();

        // Assert
        assertThat(baskets).hasSize(2);
        assertThat(baskets).containsExactlyInAnyOrder(basket1, basket2);
        verify(basketRepository, times(1)).findAll();
    }

    @Test
    void sendBasketToMessageQueueShouldSendCorrectMessage() throws JsonProcessingException {
        // Arrange
        List<Basket> baskets = new ArrayList<>();
        Basket basket = new Basket();
        basket.setId(1L);
        basket.setName("Basket");
        baskets.add(basket);

        // Act
        basketService.sendBasketToMessageQueue(baskets);

        // Assert
        verify(senderClient, times(1)).sendMessage(any());
    }
}
