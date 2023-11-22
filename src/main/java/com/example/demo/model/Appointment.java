package com.example.demo.model;

import com.example.demo.util.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "appointment_date")
    @Temporal(TemporalType.DATE)
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    @Temporal(TemporalType.TIME)
    private LocalTime appointmentTime;

    @Column(name = "work_hours")
    private Integer workHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.PENDING;

    @Column(name = "member_contact", length = 50)
    private String memberContact;

    @Column(name = "caregiver_contact", length = 50)
    private String caregiverContact;

    @ManyToOne
    @JoinColumn(
            name = "caregiver_user_id",
            referencedColumnName = "caregiver_user_id",
            foreignKey = @ForeignKey(name = "caregiver_appointment_fk"),
            nullable = false
    )
    private Caregiver caregiver;

    @ManyToOne
    @JoinColumn(
            name = "member_user_id",
            referencedColumnName = "member_user_id",
            foreignKey = @ForeignKey(name = "member_appointment_fk"),
            nullable = false
    )
    private Member member;
}
