package com.fissara.taco.ordering.system.service;

import com.fissara.taco.ordering.system.commons.exception.IngredientException;
import com.fissara.taco.ordering.system.commons.exception.OrderingException;
import com.fissara.taco.ordering.system.commons.exception.TacoException;
import com.fissara.taco.ordering.system.commons.messages.ConfirmMessage;
import com.fissara.taco.ordering.system.commons.messages.ErrorMessage;
import com.fissara.taco.ordering.system.dto.*;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Order;
import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.repository.CustomerRepository;
import com.fissara.taco.ordering.system.repository.IngredientRepository;
import com.fissara.taco.ordering.system.repository.OrderRepository;
import com.fissara.taco.ordering.system.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TacoRepository tacoRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    public CustomerOrderResponse findAlOrdersByCustomerId(Long customerId) throws OrderingException {
        CustomerOrderResponse customerOrders = new CustomerOrderResponse();
        try {
            List<CustomerOrder> customerOrderList = new LinkedList<>();
            Customer customer = customerRepository.getOne(customerId);
            CustomerDetail customerDetail = new CustomerDetail();
            customerDetail.setId(customer.getId());
            customerDetail.setName(customer.getName());
            customerDetail.setCreatedAt(customer.getCreatedAt());
            customerOrders.setCustomerDetail(customerDetail);
            for (Order o : orderRepository.findOrdersByCustomerId(customerId)) {
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setOrderId(o.getId());
                customerOrder.setCreatedAt(o.getCreatedAt());
                customerOrderList.add(customerOrder);
            }
            customerOrders.setCustomerOrders(customerOrderList);
        } catch (Exception e) {
            throw new OrderingException(e.getStackTrace().toString());
        }
        return customerOrders;
    }

    @Transactional
    public OrderResponse processOrderRequest(OrderRequest orderRequest) throws OrderingException, TacoException, IngredientException {
        try {
            for (Taco taco : orderRequest.getOrder().getTacos()) {
                if (taco.getName().length() < 5) {
                    throw new TacoException("Error: " + ErrorMessage.INVALID_TACO_NAME_ERROR);
                }
            }
            Order order = new Order();
            order.setCustomer(orderRequest.getOrder().getCustomer());
            Order createdOrder = orderRepository.save(order);
            if (createdOrder != null) {
                for (Taco taco : orderRequest.getOrder().getTacos()) {
                    if (taco.getIngredients().size() < 1) {
                        throw new IngredientException("Error: " + ErrorMessage.INGREDIENT_IS_REQUIRED_ERROR);
                    }
                    Taco newTaco = new Taco();
                    newTaco.setName(taco.getName());
                    newTaco.setOrder(createdOrder);
                    Taco createdTaco = tacoRepository.save(newTaco);
                    if (createdTaco != null) {
                        for (Ingredient ingredient: taco.getIngredients()) {
                            Ingredient newIngredient = new Ingredient();
                            newIngredient.setName(ingredient.getName());
                            newIngredient.setTaco(createdTaco);
                            ingredientRepository.save(newIngredient);
                        }
                    }
                }
                return new OrderResponse(createdOrder.getId(),
                        createdOrder.getCreatedAt(), ConfirmMessage.TRANSACTION_COMPLETED);
            }
        } catch (TacoException te) {
            throw new TacoException(te.getMessage());
        } catch (IngredientException ie) {
            throw new IngredientException(ie.getMessage());
        } catch (Exception e) {
            throw new OrderingException(e.getMessage());
        }
        return null;
    }
}
