/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 *
 * @author harshita.sethi
 */
@Component
public class PreAppDestroy {

    @PreDestroy
    public void destroy() throws SQLException {
        System.out.println("Shutting derby down in test...");
        DriverManager.getConnection("jdbc:derby:;shutdown=true");
    }
}
