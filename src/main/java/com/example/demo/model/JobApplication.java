package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id")
    private Long jobApplicationId;

    @Column(name = "date_applied")
    @Temporal(TemporalType.DATE)
    private LocalDate dateApplied;

    @ManyToOne
    @JoinColumn(
            name = "job_id",
            referencedColumnName = "job_id",
            foreignKey = @ForeignKey(name = "job_job_application_fk"),
            nullable = false
    )
    private Job job;

    @ManyToOne
    @JoinColumn(
            name = "caregiver_user_id",
            referencedColumnName = "caregiver_user_id",
            foreignKey = @ForeignKey(name = "caregiver_job_application_fk"),
            nullable = false
    )
    private Caregiver caregiver;
}
