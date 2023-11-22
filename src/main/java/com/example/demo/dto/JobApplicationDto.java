package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobApplicationDto {
    @JsonProperty("job_application_id")
    private Long jobApplicationId;
    @JsonProperty("date_applied")
    private LocalDate dateApplied;
    private JobDto job;
    private CaregiverDto caregiver;
}
