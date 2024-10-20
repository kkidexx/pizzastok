package com.pizza.pizzastok.controlador;

import com.pizza.pizzastok.modelo.Pizza;
import com.pizza.pizzastok.modelo.Stock;
import com.pizza.pizzastok.servicioo.PizzaService;
import com.pizza.pizzastok.servicioo.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;
    private final StockService stockService;  // Inyectamos el servicio de stock

    public PizzaController(PizzaService pizzaService, StockService stockService) {
        this.pizzaService = pizzaService;
        this.stockService = stockService; // Inicializamos el servicio de stock
    }

    // Listar Pizzas
    @GetMapping
    public String listarPizzas(Model model) {
        List<Pizza> pizzas = pizzaService.obtenerTodasLasPizzas();
        model.addAttribute("pizzas", pizzas);
        return "pizzas";
    }

    // Mostrar formulario para crear nueva pizza
    @GetMapping("/crear")
    public String crearPizzaForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "crearPizza";
    }

    // Crear pizza
    @PostMapping
    public String crearPizza(@ModelAttribute Pizza pizza) {
        pizzaService.crearPizza(pizza);

        // Después de crear la pizza, creamos una entrada en el stock
        Stock stock = new Stock();
        stock.setPizza(pizza);
        stock.setCantidad(0);  // Puedes cambiar este valor por un número inicial
        stockService.crearStock(stock);

        return "redirect:/pizzas";
    }

    // Mostrar formulario para editar una pizza existente
    @GetMapping("/editar/{id}")
    public String editarPizzaForm(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.obtenerPizzaPorId(id);
        model.addAttribute("pizza", pizza);
        return "editarPizza";
    }

    // Procesar la edición de la pizza
    @PostMapping("/editar/{id}")
    public String actualizarPizza(@PathVariable Long id, @ModelAttribute Pizza pizza) {
        Pizza pizzaExistente = pizzaService.obtenerPizzaPorId(id);
        if (pizzaExistente != null) {
            pizzaExistente.setNombre(pizza.getNombre());
            pizzaExistente.setPrecio(pizza.getPrecio());
            pizzaService.actualizarPizza(pizzaExistente);
        }
        return "redirect:/pizzas";
    }

    // Eliminar pizza
    @GetMapping("/eliminar/{id}")
    public String eliminarPizza(@PathVariable Long id) {
        pizzaService.eliminarPizza(id);
        return "redirect:/pizzas";
    }
}