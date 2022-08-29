/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infogain.reward.repository;

import com.infogain.reward.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author harshita.sethi
 */
public interface CustomerRespository extends JpaRepository<Customer, Long> {

}
