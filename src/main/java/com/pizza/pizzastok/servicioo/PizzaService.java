package com.pizza.pizzastok.servicioo;

import com.pizza.pizzastok.modelo.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> obtenerTodasLasPizzas();
    Pizza obtenerPizzaPorId(Long id);
    Pizza crearPizza(Pizza pizza);
    void eliminarPizza(Long id);

    // Método para actualizar
    Pizza actualizarPizza(Pizza pizza);
}
