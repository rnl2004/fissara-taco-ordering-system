package com.fissara.taco.ordering.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fissara.taco.ordering.system.commons.messages.ConfirmMessage;
import com.fissara.taco.ordering.system.dto.OrderRequest;
import com.fissara.taco.ordering.system.dto.OrderResponse;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.model.Ingredient;
import com.fissara.taco.ordering.system.model.Order;
import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("testuser")
    @Test
    public void placeOrderTest() throws Exception {
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1L);
        mockCustomer.setName("Test Customer");
        mockCustomer.setCreatedAt(null);
        mockCustomer.setUpdatedAt(null);

        Order mockOrder = new Order();
        mockOrder.setCustomer(mockCustomer);

        Taco taco = new Taco();
        taco.setId(1L);
        taco.setName("Test Taco");

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Test Ingredient");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Test Ingredient 2");

        List<Ingredient> ingredientList = new LinkedList<>();
        ingredientList.add(ingredient);
        ingredientList.add(ingredient2);

        taco.setIngredients(ingredientList);

        List<Taco> tacoList = new LinkedList<>();
        tacoList.add(taco);
        mockOrder.setTacos(tacoList);

        OrderRequest mockOrderRequest = new OrderRequest();
        mockOrderRequest.setOrder(mockOrder);

        // This will hold the mock response after placing an order
        OrderResponse mockOrderResponse = new OrderResponse();
        mockOrderResponse.setOrderId(1L);
        mockOrderResponse.setMessage(ConfirmMessage.TRANSACTION_COMPLETED);
        mockOrderResponse.setCreateAt(null);

        String orderResponseInJson = this.mapToJson(mockOrderResponse);

        String inputInJson = this.mapToJson(mockOrderRequest);

        String URI = "/api/order/placeOrder";

        Mockito.when(orderService.processOrderRequest(Mockito.any(OrderRequest.class))).thenReturn(mockOrderResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI).accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(orderResponseInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

