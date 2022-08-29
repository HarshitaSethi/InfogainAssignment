/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.repository;

import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.model.Reward;
import com.infogain.reward.model.RewardPerCustomer;
import com.infogain.reward.service.RewardService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author harshita.sethi
 */
@SpringBootTest
public class RewardRepositoryTest {
    
    @Autowired
    private RewardRepository rewardRepository;
    
    private Reward mockReward;
    
    private Reward recordCreated;
    
    @BeforeEach
    public void setUp() throws ParseException {
        mockReward = new Reward();
        mockReward.setCustomerId(1L);
        mockReward.setMonth("May");
        mockReward.setRewardPoints(20);
        String date = "10-05-2022";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        mockReward.setTransactionDate(sdf.parse(date));
        
        recordCreated = rewardRepository.save(mockReward);
    }
    
    @AfterEach
    public void tearDown() {
        rewardRepository.deleteById(recordCreated.getRewardId());
    }

    /**
     * Test of findByCustomerId method, of class RewardRepository.
     */
    @Test
    public void testFindByCustomerId() {
        List<Reward> rewards = rewardRepository.findByCustomerId(mockReward.getCustomerId());
        assertTrue(!rewards.isEmpty());
    }

    /**
     * Test of getRewardsPerMonth method, of class RewardRepository.
     */
    @Test
    public void testGetRewardsPerMonth() {
        List<RewardPerCustomer> rewards = rewardRepository.getRewardsPerMonth();
        assertTrue(!rewards.isEmpty());
        
    }

    /**
     * Test of getRewardsPerMonthByCustomer method, of class RewardRepository.
     */
    @Test
    public void testGetRewardsPerMonthByCustomer() {
        List<RewardPerCustomer> rewards = rewardRepository.getRewardsPerMonthByCustomer(mockReward.getCustomerId());
        assertTrue(!rewards.isEmpty());
    }

    /**
     * Test of getTotalRewards method, of class RewardRepository.
     */
    @Test
    public void testGetTotalRewards() {
        List<RewardPerCustomer> rewards = rewardRepository.getTotalRewards();
        assertTrue(!rewards.isEmpty());
    }

    /**
     * Test of getTotalRewardsByCustomer method, of class RewardRepository.
     */
    @Test
    public void testGetTotalRewardsByCustomer() {
        List<RewardPerCustomer> rewards = rewardRepository.getTotalRewardsByCustomer(mockReward.getCustomerId());
        assertTrue(!rewards.isEmpty());
    }
    
}
