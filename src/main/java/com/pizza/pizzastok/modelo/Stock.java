package com.pizza.pizzastok.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "idPizza")
    private Pizza pizza;

    // Getters y Setters
}
