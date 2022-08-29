/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infogain.reward.repository;

import com.infogain.reward.model.CustomerTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author harshita.sethi
 */
public interface TransactionRepository extends JpaRepository<CustomerTransaction, Long> {

    List<CustomerTransaction> findByCustomerId(Long customerId);

}
