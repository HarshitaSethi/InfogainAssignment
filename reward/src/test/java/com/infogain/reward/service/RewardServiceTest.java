/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.service;

import com.infogain.reward.model.CustomerTransaction;
import com.infogain.reward.model.Reward;
import com.infogain.reward.model.RewardPerCustomer;
import com.infogain.reward.repository.RewardRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author harshita.sethi
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RewardServiceTest {

    @MockBean
    private RewardRepository rewardRepository;

    @Autowired
    private RewardService rewardService;

    private Reward mockReward;
    private RewardPerCustomer mockRewardPerCustomer;
    private RewardPerCustomer mockRewardPerCustomerTotal;
    private CustomerTransaction mockCustomerTransaction;
    private CustomerTransaction mockCustomerTransactionLessThan50;

    @BeforeEach
    public void setUp() throws ParseException {
        String date = "19-09-2021";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        mockReward = new Reward(1L, 1L, sdf.parse(date), "September", 110);
        mockRewardPerCustomer = new RewardPerCustomer(1L, "September", 110L);
        mockRewardPerCustomerTotal = new RewardPerCustomer(1L, null, 110L);
        mockCustomerTransaction = new CustomerTransaction(1L, 1L, sdf.parse(date), 120.0);
        mockCustomerTransactionLessThan50 = new CustomerTransaction(1L, 1L, sdf.parse(date), 50.0);
    }

    /**
     * Test of insertReward method, of class RewardService.
     */
    @Test
    public void testInsertReward() {
        Mockito.when(rewardRepository.save(Mockito.any(Reward.class)))
                .thenReturn(mockReward);

        Reward reward = rewardService.insertReward(mockCustomerTransaction);
        assertNotNull(reward);
    }

    @Test
    public void testInsertRewardLessThan50() {
        Mockito.when(rewardRepository.save(Mockito.any(Reward.class)))
                .thenReturn(mockReward);

        Reward reward = rewardService.insertReward(mockCustomerTransactionLessThan50);
        assertNull(reward);
    }

    /**
     * Test of getAllRewards method, of class RewardService.
     */
    @Test
    public void testGetAllRewards() {
        Mockito.when(rewardRepository.findAll())
                .thenReturn(List.of(mockReward));

        List<Reward> rewards = rewardService.getAllRewards();
        assertTrue(rewards.size() > 0);
    }

    /**
     * Test of getAllRewardsByCustomer method, of class RewardService.
     */
    @Test
    public void testGetAllRewardsByCustomer() {
        Mockito.when(rewardRepository.findByCustomerId(Mockito.anyLong()))
                .thenReturn(List.of(mockReward));

        List<Reward> rewards = rewardService.getAllRewardsByCustomer(1L);
        assertTrue(rewards.size() > 0);
    }

    /**
     * Test of getRewardsPerMonth method, of class RewardService.
     */
    @Test
    public void testGetRewardsPerMonth() {
        Mockito.when(rewardRepository.getRewardsPerMonth())
                .thenReturn(List.of(mockRewardPerCustomer));

        List<RewardPerCustomer> rewardsPerMonth = rewardService.getRewardsPerMonth();
        assertTrue(rewardsPerMonth.size() > 0);
    }

    /**
     * Test of getRewardsPerMonthByCustomer method, of class RewardService.
     */
    @Test
    public void testGetRewardsPerMonthByCustomer() {
        Mockito.when(rewardRepository.getRewardsPerMonthByCustomer(Mockito.anyLong()))
                .thenReturn(List.of(mockRewardPerCustomer));

        List<RewardPerCustomer> rewardsPerMonthByCustomers = rewardService.getRewardsPerMonthByCustomer(1L);
        assertTrue(rewardsPerMonthByCustomers.size() > 0);
    }

    /**
     * Test of getTotalRewards method, of class RewardService.
     */
    @Test
    public void testGetTotalRewards() {
        Mockito.when(rewardRepository.getTotalRewards())
                .thenReturn(List.of(mockRewardPerCustomerTotal));

        List<RewardPerCustomer> totalRewards = rewardService.getTotalRewards();
        assertTrue(totalRewards.size() > 0);
    }

    /**
     * Test of getTotalRewardsByCustomer method, of class RewardService.
     */
    @Test
    public void testGetTotalRewardsByCustomer() {
        Mockito.when(rewardRepository.getTotalRewardsByCustomer(Mockito.anyLong()))
                .thenReturn(List.of(mockRewardPerCustomerTotal));

        List<RewardPerCustomer> totalRewardsByCustomer = rewardService.getTotalRewardsByCustomer(1L);
        assertTrue(totalRewardsByCustomer.size() > 0);
    }

    @Test
    public void testCalculateReward() {
        int rewardPoint = rewardService.calculateReward(120.0);
        assertEquals(90, rewardPoint);
    }

    @Test
    public void testCalculateRewardLessThan50() {
        int rewardPoint = rewardService.calculateReward(49.0);
        assertEquals(0, rewardPoint);
    }

    @Test
    public void testCalculateRewardEqualTo50() {
        int rewardPoint = rewardService.calculateReward(50.0);
        assertEquals(0, rewardPoint);
    }

    @Test
    public void testCalculateRewardEqualTo50Decimal() {
        int rewardPoint = rewardService.calculateReward(50.3);
        assertEquals(0, rewardPoint);
    }

    @Test
    public void testCalculateRewardGreaterThan50LessThan100() {
        int rewardPoint = rewardService.calculateReward(73);
        assertEquals(23, rewardPoint);
    }

    @Test
    public void testCalculateRewardGreaterThan50LessThan100Decimal() {
        int rewardPoint = rewardService.calculateReward(73.4);
        assertEquals(23, rewardPoint);
    }

    @Test
    public void testCalculateRewardEqualTo100() {
        int rewardPoint = rewardService.calculateReward(100);
        assertEquals(50, rewardPoint);
    }

    @Test
    public void testCalculateRewardEqualTo100Decimal() {
        int rewardPoint = rewardService.calculateReward(100.4);
        assertEquals(50, rewardPoint);
    }

    @Test
    public void testCalculateRewardGreaterThan100() {
        int rewardPoint = rewardService.calculateReward(110);
        assertEquals(70, rewardPoint);
    }

    @Test
    public void testCalculateRewardGreaterThan100Decimal() {
        int rewardPoint = rewardService.calculateReward(130.4);
        assertEquals(110, rewardPoint);
    }

    @Test
    public void testgetMonth() throws ParseException {
        String date = "19-09-2021";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String month = rewardService.getMonth(sdf.parse(date));
        assertEquals("September", month);
    }

}
