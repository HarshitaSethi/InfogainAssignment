/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.service;

import com.infogain.reward.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infogain.reward.repository.CustomerRepository;

/**
 *
 * @author harshita.sethi
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRespository;

    public Customer insertCustomer(Customer customer) {
        return customerRespository.saveAndFlush(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRespository.findAll();
    }
}
