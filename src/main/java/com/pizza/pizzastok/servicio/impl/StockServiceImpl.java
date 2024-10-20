package com.pizza.pizzastok.servicio.impl;

import com.pizza.pizzastok.modelo.Stock;
import com.pizza.pizzastok.repositorio.StockRepository;
import com.pizza.pizzastok.servicioo.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> obtenerTodoElStock() {
        return stockRepository.findAll();
    }

    @Override
    public Stock obtenerStockPorId(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock crearStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public void eliminarStock(Long id) {
        stockRepository.deleteById(id);
    }
}