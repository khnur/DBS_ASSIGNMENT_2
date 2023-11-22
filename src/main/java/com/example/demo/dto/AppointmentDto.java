package com.example.demo.dto;

import com.example.demo.util.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDto {
    @JsonProperty("appointment_id")
    private Long appointmentId;
    @JsonProperty("appointment_date")
    private LocalDate appointmentDate;
    @JsonProperty("appointment_time")
    private LocalTime appointmentTime;
    @JsonProperty("work_hours")
    private Integer workHours;
    private AppointmentStatus status = AppointmentStatus.PENDING;
    @JsonProperty("member_contact")
    private String memberContact;
    @JsonProperty("caregiver_contact")
    private String caregiverContact;
    private CaregiverDto caregiver;
    private MemberDto member;
}
