package com.pizza.pizzastok.modelo;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Pizza {

    @Id
    // Quitar la estrategia de generación automática
    private Long idPizza;

    private String nombre;
    private double precio;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Stock> stock;
}
