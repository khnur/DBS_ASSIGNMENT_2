package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberDto {
    @JsonProperty("member_user_id")
    private Long memberUserId;
    @JsonProperty("house_roles")
    private String houseRules;
    private UserDto user;
}
