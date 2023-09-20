package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.dto.RewardPunishDto;
import com.hrm.hrm.entity.RewardPunish;

import java.util.List;

public interface IrewardPunishService {
    List<RewardPunish> getAllRewardPunish();
    List<RewardPunish> getRewardPunishByUserId(int id);
    void CreateRewardPunish(RewardPunishDto dto);
}
