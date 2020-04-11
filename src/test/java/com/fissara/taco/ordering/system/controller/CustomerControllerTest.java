package com.fissara.taco.ordering.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fissara.taco.ordering.system.model.Customer;
import com.fissara.taco.ordering.system.service.CustomerService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Ignore
    @Test
    public void createCustomerTest() throws Exception {
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1L);
        mockCustomer.setName("Test Customer");
        mockCustomer.setCreatedAt(null);
        mockCustomer.setUpdatedAt(null);

        String inputInJson = this.mapToJson(mockCustomer);

        String URI = "/api/customer/create";

        Mockito.when(customerService.create(Mockito.any(Customer.class))).thenReturn(mockCustomer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI).accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

