/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.controller;

import com.infogain.reward.model.Customer;
import com.infogain.reward.service.CustomerService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author harshita.sethi
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer mockCustomer;

    private String postRequestJson = " {\"customerName\": \"Online Customer\"}";

    @BeforeEach
    public void setUp() {
        mockCustomer = new Customer(1L, "Online Customer");
    }

    /**
     * Test of getAllCustomers method, of class CustomerController.
     */
    @Test
    public void testGetAllCustomers() throws Exception {
        Mockito.when(customerService.getAllCustomers()).thenReturn(Arrays.asList(mockCustomer));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/customer").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"customerId\": 1,\"customerName\": \"Online Customer\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of addCustomer method, of class CustomerController.
     */
    @Test
    public void testAddCustomer() throws Exception {

        Mockito.when(customerService.insertCustomer(Mockito.any(Customer.class)))
                .thenReturn(mockCustomer);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customer")
                .accept(MediaType.APPLICATION_JSON).content(postRequestJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }
}
