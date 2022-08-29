/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.service;

import com.infogain.reward.model.Customer;
import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author harshita.sethi
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RewardService rewardService;

    @Transactional
    public CustomerTransaction insertTransaction(CustomerTransaction transaction) {
        CustomerTransaction customerTransaction = transactionRepository.save(transaction);
        rewardService.insertReward(transaction);
        
        return customerTransaction;
    }

    public List<CustomerTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<CustomerTransaction> getAllTransactionsForCustomer(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }
}
