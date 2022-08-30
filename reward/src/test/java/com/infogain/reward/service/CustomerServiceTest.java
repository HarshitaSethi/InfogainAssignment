/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.service;

import com.infogain.reward.model.Customer;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.infogain.reward.repository.CustomerRepository;

/**
 *
 * @author harshita.sethi
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRespository;

    @Autowired
    private CustomerService customerService;

    private Customer mockCustomer;

    @BeforeEach
    public void setUp() {
        mockCustomer = new Customer(1L, "Online Customer");
    }

     @BeforeAll
    public static void setUpClass() {
    }
    
    /**
     * Test of insertCustomer method, of class CustomerService.
     */
    @Test
    public void testInsertCustomer() {
        given(customerRespository.saveAndFlush(Mockito.any(Customer.class))).willReturn(mockCustomer);

        Mockito.when(customerRespository.saveAndFlush(Mockito.any(Customer.class)))
                .thenReturn(mockCustomer);

        Customer customer = customerService.insertCustomer(mockCustomer);
        assertNotNull(customer);
    }

    /**
     * Test of getAllCustomers method, of class CustomerService.
     */
    @Test
    public void testGetAllCustomers() {
        given(customerRespository.findAll()).willReturn(Arrays.asList(mockCustomer));

        List<Customer> customers = customerService.getAllCustomers();

        assertTrue(customers.size() > 0);
    }

}
