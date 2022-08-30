/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.service;

import com.infogain.reward.model.Reward;
import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.model.RewardPerCustomer;
import com.infogain.reward.repository.RewardRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author harshita.sethi
 */
@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Reward insertReward(CustomerTransaction transaction) {
        Reward reward = null;
        int rewardPoints = calculateReward(transaction.getTransactionAmount());
        if (rewardPoints > 0) {
            reward = new Reward();
            reward.setCustomerId(transaction.getCustomerId());
            reward.setTransactionDate(transaction.getTransactionDate());

            reward.setMonth(getMonth(reward.getTransactionDate()));
            reward.setRewardPoints(rewardPoints);

            rewardRepository.save(reward);
        }

        return reward;
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public List<Reward> getAllRewardsByCustomer(Long customerId) {
        return rewardRepository.findByCustomerId(customerId);
    }

    public List<RewardPerCustomer> getRewardsPerMonth() {
        List<RewardPerCustomer> rewards = rewardRepository.getRewardsPerMonth();
        return rewards;
    }

    public List<RewardPerCustomer> getRewardsPerMonthByCustomer(Long customerId) {
        List<RewardPerCustomer> rewards = rewardRepository.getRewardsPerMonthByCustomer(customerId);
        return rewards;
    }

    public List<RewardPerCustomer> getTotalRewards() {
        List<RewardPerCustomer> rewards = rewardRepository.getTotalRewards();
        return rewards;
    }

    public List<RewardPerCustomer> getTotalRewardsByCustomer(Long customerId) {
        List<RewardPerCustomer> rewards = rewardRepository.getTotalRewardsByCustomer(customerId);
        return rewards;
    }

    public int calculateReward(double amount) {
        if (amount <= 50.0) {
            return 0;
        }
        int dollarOver100 = 0;
        int dollarOver50 = 0;
        if (amount > 100) {
            dollarOver100 = (int) (amount - 100.0);
            dollarOver50 = 100 - 50;
        } else {
            dollarOver50 = (int) (amount - 50.0);
        }

        int totalRewards = (dollarOver100 * 2) + (dollarOver50 * 1);

        return totalRewards;
    }

    public String getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

}
