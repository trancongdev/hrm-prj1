package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.dto.ComplainDto;
import com.hrm.hrm.entity.Complain;

import java.util.List;

public interface IComplainService {
    List<Complain> GetAllComplain();
    void CreateComplain(ComplainDto dto);
    List<Complain> GetComplainByUserId(int id);
}
