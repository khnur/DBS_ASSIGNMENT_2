package com.example.demo.dto;

import com.example.demo.util.CaregivingType;
import com.example.demo.util.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CaregiverDto {
    @JsonProperty("caregiver_user_id")
    private Long caregiverUserId;
    private byte[] photo;
    private Gender gender;
    @JsonProperty("caregiving_type")
    private CaregivingType caregivingType;
    @JsonProperty("hourly_rate")
    private BigDecimal hourlyRate;
    @JsonProperty("available_for_contact")
    private Boolean availableForContact = true;
    @NotNull
    private UserDto user;
}
