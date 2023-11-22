package com.example.demo.model;

import com.example.demo.util.CaregivingFrequency;
import com.example.demo.util.CaregivingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Enumerated(EnumType.STRING)
    @Column(name = "required_caregiving_type", nullable = false)
    private CaregivingType requiredCaregivingType;

    @Column(name = "careneed_person_age")
    private Integer careneedPersonAge;

    @Column(name = "preferred_time_interval", length = 100)
    private String preferredTimeInterval;

    @Enumerated(EnumType.STRING)
    @Column(name = "caregiving_frequency")
    private CaregivingFrequency caregivingFrequency;

    @Column(name = "other_requirements", columnDefinition = "TEXT")
    private String otherRequirements;

    @Column(name = "date_posted")
    @Temporal(TemporalType.DATE)
    private LocalDate datePosted;

    @ManyToOne
    @JoinColumn(
            name = "member_user_id",
            referencedColumnName = "member_user_id",
            foreignKey = @ForeignKey(name = "member_job_fk"),
            nullable = false
    )
    private Member member;

    @OneToMany(
            mappedBy = "job",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobApplication> jobApplications = new ArrayList<>();
}
