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

    private Long customeId;
    private String month;
    private Long rewardPoints;

    public RewardPerCustomer(Long customeId, String month, Long rewardPoints) {
        this.customeId = customeId;
        this.month = month;
        this.rewardPoints = rewardPoints;
    }

    public RewardPerCustomer(Long customeId, Long rewardPoints) {
        this.customeId = customeId;
        this.rewardPoints = rewardPoints;
    }

    public RewardPerCustomer() {
    }

    public Long getCustomeId() {
        return customeId;
    }

    public void setCustomeId(Long customeId) {
        this.customeId = customeId;
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
