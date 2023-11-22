package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    @JsonProperty("user_id")
    private Long userId;
    private String email;
    @JsonProperty("given_name")
    private String givenName;
    private String surname;
    private String city;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("profile_description")
    private String profileDescription;
    @JsonProperty("allow_contact")
    private Boolean allowContact = false;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
