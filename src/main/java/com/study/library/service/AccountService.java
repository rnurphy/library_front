package com.study.library.service;

import com.study.library.dto.EditPasswordReqDto;
import com.study.library.entity.User;
import com.study.library.exception.ValidException;
import com.study.library.repository.UserMapper;
import com.study.library.security.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void editPassword(EditPasswordReqDto editPasswordReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userMapper.findUserByUsername(authentication.getName());
        System.out.println(user.getPassword());
        System.out.println(editPasswordReqDto.getOldPassword());

        if(!passwordEncoder.matches(editPasswordReqDto.getOldPassword(), user.getPassword())) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증 실패\n다시 입력 바랍니다."));
        }

        if(!editPasswordReqDto.getNewPassword().equals(editPasswordReqDto.getNewPasswordCheck())) {
            throw new ValidException(Map.of("newPasswordCheck", "비밀번호 불일치\n다시 입력 바랍니다."));
        }

        if(passwordEncoder.matches(editPasswordReqDto.getNewPasswordCheck(), user.getPassword())) {
            throw new ValidException(Map.of("newPassword", "이전 비밀번호와 동일한 비밀번호는 사용할 수 없습니다."));
        }

        user.setPassword(passwordEncoder.encode(editPasswordReqDto.getNewPassword()));

        userMapper.modifyPassword(user);

    }


















}
