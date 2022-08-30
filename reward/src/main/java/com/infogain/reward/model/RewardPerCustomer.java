/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.model;

/**
 *
 * @author harshita.sethi
 */
public class RewardPerCustomer {

    private Long customerId;
    private String month;
    private Long rewardPoints;

    public RewardPerCustomer(Long customerId, String month, Long rewardPoints) {
        this.customerId = customerId;
        this.month = month;
        this.rewardPoints = rewardPoints;
    }

    public RewardPerCustomer(Long customerId, Long rewardPoints) {
        this.customerId = customerId;
        this.rewardPoints = rewardPoints;
    }

    public RewardPerCustomer() {
    }

    public Long getCustomeId() {
        return customerId;
    }

    public void setCustomeId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

}
