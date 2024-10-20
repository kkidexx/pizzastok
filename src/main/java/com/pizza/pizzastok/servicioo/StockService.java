package com.pizza.pizzastok.servicioo;

import com.pizza.pizzastok.modelo.Stock;

import java.util.List;

public interface StockService {

    List<Stock> obtenerTodoElStock();
    Stock obtenerStockPorId(Long id);
    Stock crearStock(Stock stock);
    void eliminarStock(Long id);
}
