/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.controller;

import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author harshita.sethi
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<CustomerTransaction>> getAllTransactions() {
        List<CustomerTransaction> allTransactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{customerId}")
    public ResponseEntity<List<CustomerTransaction>> getAllTransactionsByCustomer(@PathVariable("customerId") Long customerId) {
        List<CustomerTransaction> allTransactions = transactionService.getAllTransactionsForCustomer(customerId);
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addTransaction(@RequestBody final CustomerTransaction transaction) {
        System.out.println("transaction -> " + transaction);
        try {
            transactionService.insertTransaction(transaction);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to insert a new transaction due to " + ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction added");
    }
}
