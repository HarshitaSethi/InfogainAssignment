/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.repository;

import com.infogain.reward.model.Customer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author harshita.sethi
 */
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRespository;
    private Customer mockCustomer;

    @BeforeEach
    public void setUp() {
        mockCustomer = new Customer();
        mockCustomer.setCustomerName("Customer 101");
    }

    @Test
    public void testSaveAndFind() {

        Customer customer = customerRespository.saveAndFlush(mockCustomer);

        assertEquals("Customer 101", customer.getCustomerName());

        List<Customer> customers = customerRespository.findAll();

        assertTrue(!customers.isEmpty());

        customerRespository.deleteById(customer.getCustomerId());
    }

}
