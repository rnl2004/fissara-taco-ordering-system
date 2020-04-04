package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.commons.exception.IngredientException;
import com.fissara.taco.ordering.system.commons.exception.OrderingException;
import com.fissara.taco.ordering.system.commons.exception.TacoException;
import com.fissara.taco.ordering.system.dto.CustomerOrderResponse;
import com.fissara.taco.ordering.system.dto.OrderRequest;
import com.fissara.taco.ordering.system.dto.OrderResponse;
import com.fissara.taco.ordering.system.model.Order;
import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.service.OrderService;
import com.fissara.taco.ordering.system.service.TacoService;
import lombok.RequiredArgsConstructor;
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

    /**
     *  This will take place the order process
     * @param orderRequest composed of customer who ordered and order details as request body
     * @return
     * @throws OrderingException
     */
    @PostMapping("/placeOrder")
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) throws OrderingException, IngredientException, TacoException {
        return orderService.processOrderRequest(orderRequest);
    }

    /**
     * Get all customer orders
     * @param id customer id as required parameter
     * @return
     * @throws OrderingException
     */
    @GetMapping("/findAlOrdersByCustomer")
    public CustomerOrderResponse findAlOrdersByCustomer(@RequestParam(value = "id") Long id) throws OrderingException {
        return orderService.findAlOrdersByCustomerId(id);
    }
}
