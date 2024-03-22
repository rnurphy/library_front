package com.study.library.dto;

import com.study.library.entity.OAuth2;
import com.study.library.entity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OAuth2SignupReqDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$",
            message = "대소문자, 숫자 5 ~ 10자리 형식이어야 합니다.")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$",
            message = "영문, 특문, 숫자를 포함한 8 ~ 128자리 형식이어야 합니다.")
    private String password;

    @Pattern(regexp = "^[가-힇]{1,}$",
            message = "최소 한글 2자입니다.")
    private String name;

    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
            message = "이메일 형식이어야 합니다.")
    private String email;

    @NotBlank
    private String oauth2Name;
    @NotBlank
    private String providerName;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .build();
    }

    public OAuth2 toOAuth2Entity(int userId) {
        return OAuth2.builder()
                .oAuth2Name(oauth2Name)
                .userId(userId)
                .providerName(providerName)
                .build();
    }






























}

