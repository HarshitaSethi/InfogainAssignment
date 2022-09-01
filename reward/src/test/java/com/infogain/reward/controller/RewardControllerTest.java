/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.infogain.reward.controller;

import com.infogain.reward.model.Reward;
import com.infogain.reward.model.RewardPerCustomer;
import com.infogain.reward.service.RewardService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author harshita.sethi
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    private Reward reward;
    private RewardPerCustomer rewardPerCustomer;
    private RewardPerCustomer rewardPerCustomerTotal;

    @BeforeEach
    public void setUp() throws ParseException {
        String date = "19-09-2021";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        reward = new Reward(1L, 1L, sdf.parse(date), "September", 110);
        rewardPerCustomer = new RewardPerCustomer(1L, "September", 110L);
        rewardPerCustomerTotal = new RewardPerCustomer(1L, null, 110L);
    }

    /**
     * Test of getAllRewards method, of class RewardController.
     */
    @Test
    public void testGetAllRewards() throws Exception {
        Mockito.when(rewardService.getAllRewards())
                .thenReturn(List.of(reward));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{ \"rewardId\": 1, \"customerId\": 1, \"transactionDate\": \"19-09-2021\", \"month\": \"September\", \"rewardPoints\": 110 }]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getAllRewardsByCustomer method, of class RewardController.
     */
    @Test
    public void testGetAllRewardsByCustomer() throws Exception {
        Mockito.when(rewardService.getAllRewardsByCustomer(Mockito.anyLong()))
                .thenReturn(List.of(reward));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{ \"rewardId\": 1, \"customerId\": 1, \"transactionDate\": \"19-09-2021\", \"month\": \"September\", \"rewardPoints\": 110 }]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    /**
     * Test of getRewardPerMonth method, of class RewardController.
     */
    @Test
    public void testGetRewardPerMonth() throws Exception {
        Mockito.when(rewardService.getRewardsPerMonth())
                .thenReturn(List.of(rewardPerCustomer));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/rewardPerMonth").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{ \"customeId\": 1, \"month\": \"September\", \"rewardPoints\": 110 }]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    /**
     * Test of getRewardPerMonthByCustomer method, of class RewardController.
     */
    @Test
    public void testGetRewardPerMonthByCustomer() throws Exception {
        Mockito.when(rewardService.getRewardsPerMonthByCustomer(Mockito.anyLong()))
                .thenReturn(List.of(rewardPerCustomer));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/rewardPerMonth/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{ \"customeId\": 1, \"month\": \"September\", \"rewardPoints\": 110 }]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getTotalReward method, of class RewardController.
     */
    @Test
    public void testGetTotalReward() throws Exception {
        Mockito.when(rewardService.getTotalRewards())
                .thenReturn(List.of(rewardPerCustomerTotal));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/total").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{ \"customeId\": 1, \"month\": null, \"rewardPoints\": 110 }]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getTotalRewardByCustomer method, of class RewardController.
     */
    @Test
    public void testGetTotalRewardByCustomer() throws Exception {
        Mockito.when(rewardService.getTotalRewardsByCustomer(Mockito.anyLong()))
                .thenReturn(List.of(rewardPerCustomerTotal));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/reward/total/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{ \"customeId\": 1, \"month\": null, \"rewardPoints\": 110 }]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}
