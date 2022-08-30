/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author harshita.sethi
 */
@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;
    private Long customerId;

    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    
    private String month;
    private int rewardPoints;

    public Reward() {
    }

    public Reward(Long rewardId, Long customerId, Date transactionDate, String month, int rewardPoints) {
        this.rewardId = rewardId;
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.month = month;
        this.rewardPoints = rewardPoints;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Reward{" + "rewardId=" + rewardId + ", customerId=" + customerId + ", transactionDate=" + transactionDate + ", month=" + month + ", rewardPoints=" + rewardPoints + '}';
    }

}
