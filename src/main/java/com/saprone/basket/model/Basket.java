package com.saprone.basket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Basket {
    @Id
    private Integer id;
    private String name;
}
