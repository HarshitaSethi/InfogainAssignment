/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.repository;

import com.infogain.reward.model.Customer;
import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.model.Reward;
import com.infogain.reward.service.RewardService;
import com.infogain.reward.service.TransactionService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author harshita.sethi
 */
@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    private CustomerTransaction mockCustomerTransaction;

    @BeforeEach
    public void setUp() throws ParseException {
        String date = "10-05-2022";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        mockCustomerTransaction = new CustomerTransaction(1L, 1L, sdf.parse(date), 120.0);
    }

    @Test
    public void testSaveAndFind() {

        CustomerTransaction customerTransaction = transactionRepository.saveAndFlush(mockCustomerTransaction);

        assertEquals(120.0, customerTransaction.getTransactionAmount());

        List<CustomerTransaction> findAllCustomerTransactions = transactionRepository.findAll();

        assertTrue(!findAllCustomerTransactions.isEmpty());

        List<CustomerTransaction> findByCustomerId = transactionRepository.findByCustomerId(customerTransaction.getCustomerId());

        assertEquals(120.0, findByCustomerId.get(0).getTransactionAmount());

        transactionRepository.deleteById(customerTransaction.getTransactionId());
    }

}
