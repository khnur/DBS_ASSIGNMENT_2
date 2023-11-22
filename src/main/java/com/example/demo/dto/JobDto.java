package com.example.demo.dto;

import com.example.demo.util.CaregivingFrequency;
import com.example.demo.util.CaregivingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobDto {
    @JsonProperty("job_id")
    private Long jobId;
    @JsonProperty("required_caregiving_type")
    private CaregivingType requiredCaregivingType;
    @JsonProperty("careneed_person_age")
    private Integer careneedPersonAge;
    @JsonProperty("preferred_time_interval")
    private String preferredTimeInterval;
    @JsonProperty("caregiving_frequency")
    private CaregivingFrequency caregivingFrequency;
    @JsonProperty("other_requirements")
    private String otherRequirements;
    @JsonProperty("date_posted")
    private LocalDate datePosted;
    private MemberDto member;
}
