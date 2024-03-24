package com.example.metricsproducer.dto;

import com.example.metricsproducer.entity.UserInfo;
import lombok.*;

@Data
@NoArgsConstructor
public class UserInfoDTO {
    private String name;
    private String email;
    private String password;
    private String roles;

    public UserInfo toUserInfo() {
        return UserInfo.builder()
                .name(this.getName())
                .email(this.getEmail())
                .password(this.getPassword())
                .roles(this.getRoles())
                .build();
    }

}

