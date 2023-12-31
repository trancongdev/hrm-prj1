package com.hrm.hrm.Presentation_Layer;

import com.hrm.hrm.Business_Logic_Layer.IUserService;
import com.hrm.hrm.Business_Logic_Layer.IrewardPunishService;
import com.hrm.hrm.Business_Logic_Layer.RewardPunishService;
import com.hrm.hrm.dto.RewardPunishDto;
import com.hrm.hrm.entity.RewardPunish;
import com.hrm.hrm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/rewardpunish")
public class RewardPunishController {
    @Autowired
    IrewardPunishService rewardPunishService;


    @GetMapping
    public ResponseEntity<?> GetAllRewardPunish(){
        return new ResponseEntity<>(rewardPunishService.getAllRewardPunish(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> GetRewardPunishByUserId(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(rewardPunishService.getRewardPunishByUserId(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> CreateRewardPunish(@RequestBody RewardPunishDto dto){
       rewardPunishService.CreateRewardPunish(dto);
       return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
}
