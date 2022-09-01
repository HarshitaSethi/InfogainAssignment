/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.controller;

import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.service.TransactionService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
@WebMvcTest(value = TransactionController.class)
public class TransactionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TransactionService transactionService;
    
    private CustomerTransaction mockCustomerTransaction;
    String postRequestJson = "{\"customerId\": 1, \"transactionDate\": \"19-09-2021\", \"transactionAmount\": 120 }";
    
    @BeforeEach
    public void setUp() throws ParseException {
        String date = "19-09-2021";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        mockCustomerTransaction = new CustomerTransaction(1L, 1L, sdf.parse(date), 120.0);
        
    }

    /**
     * Test of getAllTransactions method, of class TransactionController.
     */
    @Test
    public void testGetAllTransactions() throws Exception {
        Mockito.when(transactionService.getAllTransactions())
                .thenReturn(List.of(mockCustomerTransaction));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transaction").accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        System.out.println(result.getResponse());
        String expected = "[{ \"transactionId\": 1, \"customerId\": 1, \"transactionDate\": \"19-09-2021\", \"transactionAmount\": 120 }]";
        
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getAllTransactionsByCustomer method, of class
     * TransactionController.
     */
    @Test
    public void testGetAllTransactionsByCustomer() throws Exception {
        Mockito.when(transactionService.getAllTransactionsForCustomer(Mockito.anyLong()))
                .thenReturn(List.of(mockCustomerTransaction));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transaction/1").accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        System.out.println(result.getResponse());
        String expected = "[{ \"transactionId\": 1, \"customerId\": 1, \"transactionDate\": \"19-09-2021\", \"transactionAmount\": 120 }]";
        
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of addTransaction method, of class TransactionController.
     */
    @Test
    public void testAddTransaction() throws Exception {
        Mockito.when(transactionService.insertTransaction(Mockito.any(CustomerTransaction.class)))
                .thenReturn(mockCustomerTransaction);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/transaction")
                .accept(MediaType.APPLICATION_JSON).content(postRequestJson)
                .contentType(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        MockHttpServletResponse response = result.getResponse();
        
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    
}
