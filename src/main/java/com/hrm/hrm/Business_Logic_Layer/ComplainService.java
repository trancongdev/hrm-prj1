package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.Data_Access_Layer.ComplainRepository;
import com.hrm.hrm.Data_Access_Layer.UserRepository;
import com.hrm.hrm.dto.ComplainDto;
import com.hrm.hrm.entity.Complain;
import com.hrm.hrm.entity.User;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
@Service
public class ComplainService implements IComplainService{

    @Autowired
    ComplainRepository complainRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Complain> GetAllComplain() {
        return complainRepository.findAll();
    }

    @Override
    public void CreateComplain(ComplainDto dto) {
        User user = userRepository.findById(dto.getUserId());
        Complain complain = dto.ToEntity();
        complain.setUser(user);
        complainRepository.save(complain);
    }

    @Override
    public List<Complain> GetComplainByUserId(int id) {
        return complainRepository.findByUser_Id(id);
    }
}
