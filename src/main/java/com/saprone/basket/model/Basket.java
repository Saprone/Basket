package com.saprone.basket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "basket", schema = "basket")
public class Basket {
    @Id
    private Long id;
    private String name;
}
