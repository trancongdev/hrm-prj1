package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.dto.AccountDto;
import com.hrm.hrm.dto.UserDto;
import com.hrm.hrm.entity.User;
import com.hrm.hrm.form.filter.UserFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IUserService extends UserDetailsService {

    public Page<User> getAll(Pageable pageable, String search, UserFilterForm filter);

    void createUser(User user);

    User findUserByEmail(String email);

    User findUserByUserName(String username);

    User findUserById(int id);

    boolean existsUserByEmail(String email);

    boolean existsUserByUserName(String userName);

    void updateUser(int id, UserDto dto);

    List<AccountDto> accountList();

    void DeleteUsers(List<Integer> ids);

}
