/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.service;

import com.infogain.reward.model.Customer;
import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.model.Reward;
import com.infogain.reward.repository.TransactionRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author harshita.sethi
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransactionServiceTest {
    
    @MockBean
    private TransactionRepository transactionRepository;
    @MockBean
    private RewardService rewardService;
    
    @Autowired
    TransactionService transactionService;
    
    private CustomerTransaction mockCustomerTransaction;
    private Reward mockReward;
    
    @BeforeEach
    public void setUp() throws ParseException {
        String date = "19-09-2021";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        mockCustomerTransaction = new CustomerTransaction(1L, 1L, sdf.parse(date), 120.0);
        mockReward = new Reward(1L, 1L, sdf.parse(date), "September", 110);
        
    }

    /**
     * Test of insertTransaction method, of class TransactionService.
     */
    @Test
    public void testInsertTransaction() {
        Mockito.when(transactionRepository.save(Mockito.any(CustomerTransaction.class)))
                .thenReturn(mockCustomerTransaction);
        Mockito.when(rewardService.insertReward(Mockito.any(CustomerTransaction.class)))
                .thenReturn(mockReward);
        
        CustomerTransaction transaction = transactionService.insertTransaction(mockCustomerTransaction);
        assertNotNull(transaction);
    }

    /**
     * Test of getAllTransactions method, of class TransactionService.
     */
    @Test
    public void testGetAllTransactions() {
        Mockito.when(transactionRepository.findAll()).thenReturn(Arrays.asList(mockCustomerTransaction));
        
        List<CustomerTransaction> transactions = transactionService.getAllTransactions();
        
        assertTrue(transactions.size() > 0);
    }

    /**
     * Test of getAllTransactionsForCustomer method, of class
     * TransactionService.
     */
    @Test
    public void testGetAllTransactionsForCustomer() {
        Mockito.when(transactionRepository.findByCustomerId(Mockito.anyLong()))
                .thenReturn(Arrays.asList(mockCustomerTransaction));
        
        List<CustomerTransaction> transactions = transactionService.getAllTransactionsForCustomer(1L);
        
        assertTrue(transactions.size() > 0);
    }
    
}
