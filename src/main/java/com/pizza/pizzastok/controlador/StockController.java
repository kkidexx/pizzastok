package com.pizza.pizzastok.controlador;

import com.pizza.pizzastok.modelo.Pizza;
import com.pizza.pizzastok.modelo.Stock;
import com.pizza.pizzastok.servicio.impl.PizzaServiceImpl;
import com.pizza.pizzastok.servicioo.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    private final PizzaServiceImpl pizzaServiceImpl;

    public StockController(StockService stockService, PizzaServiceImpl pizzaServiceImpl) {
        this.stockService = stockService;
        this.pizzaServiceImpl = pizzaServiceImpl;
    }

    @GetMapping
    public String listarStock(Model model) {
        List<Stock> stockList = stockService.obtenerTodoElStock();
        model.addAttribute("stock", stockList);
        return "stock"; // Asegúrate de que la vista se llama 'stock.html'
    }

    @GetMapping("/crear")
    public String crearStockForm(Model model) {
        Stock stock = new Stock();
        List<Pizza> pizzas = pizzaServiceImpl.obtenerTodasLasPizzas(); // Obtener todas las pizzas
        model.addAttribute("stock", stock);
        model.addAttribute("pizzas", pizzas); // Agregar la lista de pizzas al modelo
        return "crearStock"; // Asegúrate de que esta sea la vista correcta
    }


    @PostMapping
    public String crearStock(@ModelAttribute Stock stock) {
        // Ya se obtiene la pizza desde el formulario
        stockService.crearStock(stock); // Guardamos el stock
        return "redirect:/stock"; // Redirige al listar stock después de crear
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarStock(@PathVariable Long id) {
        stockService.eliminarStock(id);
        return "redirect:/stock"; // Redirige al listar stock después de eliminar
    }
    @GetMapping("/editar/{idStock}")
    public String editarStockForm(@PathVariable Long idStock, Model model) {
        Stock stock = stockService.obtenerStockPorId(idStock);
        if (stock == null) {
            return "redirect:/stock?error=stock_no_encontrado";
        }
        model.addAttribute("stock", stock);
        return "editarStock"; // Vista que permite modificar solo la cantidad y acciones
    }

    @PostMapping("/editar/{idStock}")
    public String editarStock(@PathVariable Long idStock, @ModelAttribute Stock stockActualizado) {
        Stock stock = stockService.obtenerStockPorId(idStock);
        if (stock == null) {
            return "redirect:/stock?error=stock_no_encontrado";
        }
        // Solo se actualiza la cantidad
        stock.setCantidad(stockActualizado.getCantidad());
        stockService.crearStock(stock); // Guardamos los cambios
        return "redirect:/stock";
    }


}
