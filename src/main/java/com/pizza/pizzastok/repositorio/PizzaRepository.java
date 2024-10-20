package com.pizza.pizzastok.repositorio;

import com.pizza.pizzastok.modelo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository  extends JpaRepository <Pizza, Long>{
}
