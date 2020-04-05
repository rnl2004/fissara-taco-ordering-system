package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.commons.exception.CustomerException;
import com.fissara.taco.ordering.system.commons.exception.IngredientException;
import com.fissara.taco.ordering.system.commons.exception.OrderingException;
import com.fissara.taco.ordering.system.commons.exception.TacoException;
import com.fissara.taco.ordering.system.dto.CustomerOrderResponse;
import com.fissara.taco.ordering.system.dto.OrderRequest;
import com.fissara.taco.ordering.system.dto.OrderResponse;
import com.fissara.taco.ordering.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     *  This will take place the order process
     * @param orderRequest composed of customer who ordered and order details as request body
     * @return
     * @throws OrderingException
     */
    @PostMapping(value = "/placeOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) throws OrderingException,
            IngredientException, TacoException, CustomerException {
        return orderService.processOrderRequest(orderRequest);
    }

    /**
     * Get all customer orders
     * @param id customer id as required parameter
     * @return
     * @throws OrderingException
     */
    @GetMapping(value = "/findAllOrdersByCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerOrderResponse findAllOrdersByCustomer(@RequestParam(value = "id") Long id) throws OrderingException, CustomerException {
        return orderService.findAllOrdersByCustomerId(id);
    }
}
