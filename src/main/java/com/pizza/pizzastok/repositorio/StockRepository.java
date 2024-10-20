package com.pizza.pizzastok.repositorio;

import com.pizza.pizzastok.modelo.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository <Stock, Long> {
}
