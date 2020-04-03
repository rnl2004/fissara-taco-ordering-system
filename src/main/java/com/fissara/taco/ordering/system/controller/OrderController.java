package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.dto.OrderRequest;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Order;
import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.service.IngredientService;
import com.fissara.taco.ordering.system.service.OrderService;
import com.fissara.taco.ordering.system.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TacoService tacoService;

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomer(orderRequest.getOrder().getCustomer());
        Order createOrder = orderService.save(order);
        if (createOrder != null) {
            for (Taco taco : orderRequest.getOrder().getTacos()) {
                taco.setOrder(createOrder);
                //taco.setName(taco.getName());
                Taco createTaco = tacoService.save(taco);
                if (createTaco != null) {
                    for (Ingredient ingredient: taco.getIngredients()) {
                        ingredient.setTaco(createTaco);
                        ingredientService.save(ingredient);
                    }
                }
            }
        }
        return orderService.findById(order.getId());
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }
}
