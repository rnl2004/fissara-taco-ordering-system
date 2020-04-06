package com.fissara.taco.ordering.system.service;

import com.fissara.taco.ordering.system.commons.exception.CustomerException;
import com.fissara.taco.ordering.system.commons.exception.IngredientException;
import com.fissara.taco.ordering.system.commons.exception.OrderingException;
import com.fissara.taco.ordering.system.commons.exception.TacoException;
import com.fissara.taco.ordering.system.commons.messages.ConfirmMessage;
import com.fissara.taco.ordering.system.commons.utils.Validate;
import com.fissara.taco.ordering.system.dto.*;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Order;
import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.repository.CustomerRepository;
import com.fissara.taco.ordering.system.repository.IngredientRepository;
import com.fissara.taco.ordering.system.repository.OrderRepository;
import com.fissara.taco.ordering.system.repository.TacoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TacoRepository tacoRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional
    public OrderResponse processOrderRequest(OrderRequest orderRequest) throws OrderingException, TacoException, IngredientException, CustomerException {
        logger.info("Start transactional order process. This will rollback once got an error...");
        try {
            Validate validate = new Validate();
            logger.info("Validating customer...");
            validate.validateCustomer(orderRequest.getOrder().getCustomer());

            logger.info("Validating taco...");
            validate.validateTaco(orderRequest.getOrder().getTacos());

            Order order = new Order();
            order.setCustomer(orderRequest.getOrder().getCustomer());
            Order createdOrder = orderRepository.save(order);
            if (createdOrder != null) {
                for (Taco taco : orderRequest.getOrder().getTacos()) {
                    logger.info("Validating taco ingredients...");
                    validate.validateIngredient(taco.getIngredients());

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
                logger.info(ConfirmMessage.TRANSACTION_COMPLETED);
                return new OrderResponse(createdOrder.getId(),
                        createdOrder.getCreatedAt(), ConfirmMessage.TRANSACTION_COMPLETED);
            }
        } catch (CustomerException ce) {
            throw new CustomerException(ce.getMessage());
        } catch (TacoException te) {
            throw new TacoException(te.getMessage());
        } catch (IngredientException ie) {
            throw new IngredientException(ie.getMessage());
        } catch (Exception e) {
            throw new OrderingException(e.getMessage());
        }
        return null;
    }

    /**
     * Find all orders for a certain customer
     * @param customerId a key or id used to search in the database
     * @return
     * @throws OrderingException
     */
    @Transactional(readOnly = true)
    public CustomerOrderResponse findAllOrdersByCustomerId(Long customerId) throws OrderingException {
        logger.info("Start transactional getting order process. This will rollback once got an error...");
        CustomerOrderResponse customerOrders = new CustomerOrderResponse();
        try {
            logger.info("Construct customer orders...");
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

                logger.info("Construct taco details...");
                List<TacoDetail> tacoDetails = new LinkedList<>();
                for (Taco taco : tacoRepository.findTacosByOrderId(o.getId())) {
                    TacoDetail tacoDetail = new TacoDetail();
                    tacoDetail.setId(taco.getId());
                    tacoDetail.setName(taco.getName());
                    tacoDetail.setCreatedAt(taco.getCreatedAt());

                    logger.info("Construct taco ingredients detail...");
                    List<IngredientDetail> ingredientDetails = new LinkedList<>();
                    for (Ingredient ingredient : ingredientRepository.findIngredientsByTacoId(tacoDetail.getId())) {
                        IngredientDetail ingredientDetail = new IngredientDetail();
                        ingredientDetail.setId(ingredient.getId());
                        ingredientDetail.setName(ingredient.getName());
                        ingredientDetail.setCreatedAt(ingredient.getCreatedAt());
                        ingredientDetails.add(ingredientDetail);
                    }
                    tacoDetail.setIngredients(ingredientDetails);
                    tacoDetails.add(tacoDetail);
                }
                customerOrder.setTacos(tacoDetails);
                customerOrderList.add(customerOrder);
            }

            logger.info("Final constructing of customer order details...");
            customerOrders.setCustomerOrders(customerOrderList);
        } catch (Exception e) {
            throw new OrderingException(e.getMessage());
        }
        logger.info(ConfirmMessage.TRANSACTION_COMPLETED);
        return customerOrders;
    }

}
