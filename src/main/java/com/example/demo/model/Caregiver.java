package com.example.demo.model;

import com.example.demo.util.CaregivingType;
import com.example.demo.util.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "caregiver")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_user_id")
    private Long caregiverUserId;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "caregiving_type", nullable = false)
    private CaregivingType caregivingType;

    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "available_for_contact", nullable = false)
    private Boolean availableForContact = true;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "user_caregiver_fk"),
            nullable = false,
            unique = true
    )
    private User user;

    @OneToMany(
            mappedBy = "caregiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(
            mappedBy = "caregiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<JobApplication> jobApplications = new ArrayList<>();
}
