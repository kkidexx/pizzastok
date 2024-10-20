package com.pizza.pizzastok.servicio.impl;

import com.pizza.pizzastok.modelo.Pizza;
import com.pizza.pizzastok.repositorio.PizzaRepository;
import com.pizza.pizzastok.servicioo.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> obtenerTodasLasPizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza obtenerPizzaPorId(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Override
    public Pizza crearPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public void eliminarPizza(Long id) {
        pizzaRepository.deleteById(id);
    }

    // Implementación del método actualizar
    @Override
    public Pizza actualizarPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
}
