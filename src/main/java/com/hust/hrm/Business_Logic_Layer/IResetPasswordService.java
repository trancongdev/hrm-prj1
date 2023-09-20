package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.dto.ResetPasswordDto;
import com.hrm.hrm.entity.ResetPassword;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IResetPasswordService {
    List<ResetPasswordDto> GetAllResetPassword();
    void confirmRequestResetPassword(String token) throws MessagingException, UnsupportedEncodingException;
    void refuseRequestResetPassword(String token);
    void createRequestResetPassword(String email);
    boolean existsByUser_Email(String email);

}
