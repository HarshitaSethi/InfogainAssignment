/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infogain.reward.controller;

import com.infogain.reward.model.Reward;
import com.infogain.reward.model.RewardPerCustomer;
import com.infogain.reward.service.RewardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author harshita.sethi
 */
@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    RewardService rewardService;

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {

        List<Reward> allRewards = rewardService.getAllRewards();
        System.out.println("in controller -> " + allRewards.get(0).getTransactionDate());
        return new ResponseEntity<>(allRewards, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/{customerId}")
    public ResponseEntity<List<Reward>> getAllRewardsByCustomer(@PathVariable("customerId") Long customerId) {
        List<Reward> allRewards = rewardService.getAllRewardsByCustomer(customerId);
        return new ResponseEntity<>(allRewards, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/rewardPerMonth")
    public ResponseEntity<List<RewardPerCustomer>> getRewardPerMonth() {
        List<RewardPerCustomer> allRewards = rewardService.getRewardsPerMonth();
        return new ResponseEntity<>(allRewards, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/rewardPerMonth/{customerId}")
    public ResponseEntity<List<RewardPerCustomer>> getRewardPerMonthByCustomer(@PathVariable("customerId") Long customerId) {
        List<RewardPerCustomer> allRewards = rewardService.getRewardsPerMonthByCustomer(customerId);
        return new ResponseEntity<>(allRewards, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/total")
    public ResponseEntity<List<RewardPerCustomer>> getTotalReward() {
        List<RewardPerCustomer> allRewards = rewardService.getTotalRewards();
        return new ResponseEntity<>(allRewards, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/total/{customerId}")
    public ResponseEntity<List<RewardPerCustomer>> getTotalRewardByCustomer(@PathVariable("customerId") Long customerId) {
        List<RewardPerCustomer> allRewards = rewardService.getTotalRewardsByCustomer(customerId);
        return new ResponseEntity<>(allRewards, HttpStatus.OK);
    }

}
