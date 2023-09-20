package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.Data_Access_Layer.RewardPunishRepository;
import com.hrm.hrm.Data_Access_Layer.UserRepository;
import com.hrm.hrm.dto.RewardPunishDto;
import com.hrm.hrm.entity.RewardPunish;
import com.hrm.hrm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
@Service
public class RewardPunishService implements IrewardPunishService{
    @Autowired
    RewardPunishRepository rewardPunishRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<RewardPunish> getAllRewardPunish() {
        return rewardPunishRepository.findAll();
    }

    @Override
    public List<RewardPunish> getRewardPunishByUserId(int id) {
        return rewardPunishRepository.findAllByUser_Id(id);
    }

    @Override
    public void CreateRewardPunish(RewardPunishDto dto) {
        User user = userRepository.findById(dto.getUserId());
        RewardPunish rewardPunish = dto.ToEntity();
        rewardPunish.setUser(user);
        rewardPunishRepository.save(rewardPunish);
    }
}
