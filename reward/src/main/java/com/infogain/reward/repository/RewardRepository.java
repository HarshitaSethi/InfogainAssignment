/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.infogain.reward.repository;

import com.infogain.reward.model.Reward;
import com.infogain.reward.model.RewardPerCustomer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author harshita.sethi
 */
public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findByCustomerId(Long customerId);

    @Query("select  new com.infogain.reward.model.RewardPerCustomer(r.customerId, r.month,sum(r.rewardPoints))  from Reward r group by r.month,r.customerId")
    List<RewardPerCustomer> getRewardsPerMonth();

    @Query("select new com.infogain.reward.model.RewardPerCustomer(r.customerId, r.month,sum(r.rewardPoints)) from Reward r where r.customerId = :customerId group by r.customerId, r.month")
    List<RewardPerCustomer> getRewardsPerMonthByCustomer(Long customerId);

    @Query("select  new com.infogain.reward.model.RewardPerCustomer(r.customerId,sum(r.rewardPoints))  from Reward r group by r.customerId")
    List<RewardPerCustomer> getTotalRewards();

    @Query("select new com.infogain.reward.model.RewardPerCustomer(r.customerId, sum(r.rewardPoints)) from Reward r where r.customerId = :customerId group by r.customerId")
    List<RewardPerCustomer> getTotalRewardsByCustomer(Long customerId);
}
