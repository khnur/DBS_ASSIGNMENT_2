package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.AppointmentStatus;
import com.example.demo.util.CaregivingFrequency;
import com.example.demo.util.CaregivingType;
import com.example.demo.util.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
public class ApplicationData {
    private static final int UPPER_LIMIT = 10;

    @Bean
    CommandLineRunner runner(
            UserRepo userRepo,
            CaregiverRepo caregiverRepo,
            MemberRepo memberRepo,
            AddressRepo addressRepo,
            JobRepo jobRepo,
            JobApplicationRepo jobApplicationRepo,
            AppointmentRepo appointmentRepo,
            MessageRepo messageRepo
    ) {
        return args -> {
            for (int i = 1; i <= UPPER_LIMIT * 2; i++) {
                userRepo.save(User.builder()
                        .email("user" + i + "@nu.edu.kz")
                        .givenName("name" + i)
                        .surname("surname" + i)
                        .city(i % 2 == 0 ? "Astana" : "city" + i)
                        .phoneNumber("123456789" + i)
                        .profileDescription("profile description " + i)
                        .allowContact(Boolean.TRUE.equals(i % 2 == 0))
                        .password("password" + i)
                        .build());
            }
            for (int i = 1; i <= UPPER_LIMIT; i++) {
                caregiverRepo.save(Caregiver.builder()
                        .user(userRepo.getReferenceById((long) i))
                        .gender(i % 2 == 0 ? Gender.MALE : Gender.FEMALE)
                        .caregivingType(i % 2 == 1 ? CaregivingType.BABYSITTER : CaregivingType.CAREGIVER_FOR_ELDERLY)
                        .hourlyRate(BigDecimal.valueOf(i % 2 == 1 ? 15 : 5))
                        .availableForContact(true)
                        .build());
                memberRepo.save(Member.builder()
                        .user(userRepo.getReferenceById((long) i + UPPER_LIMIT))
                        .houseRules("house rules " + i)
                        .build());

                addressRepo.saveAll(List.of(
                        Address.builder()
                                .member(memberRepo.getReferenceById((long) i))
                                .houseNumber("house number " + i)
                                .street("Turan")
                                .town("town " + i)
                                .build(),
                        Address.builder()
                                .member(memberRepo.getReferenceById((long) i))
                                .houseNumber("house number " + i)
                                .street("street " + i)
                                .town("town " + i)
                                .build()
                ));

                for (int j = 1; j < 3; j++) {
                    jobRepo.save(Job.builder()
                            .member(memberRepo.getReferenceById((long) i))
                            .requiredCaregivingType(i * j % 2 == 0 ? CaregivingType.BABYSITTER : CaregivingType.CAREGIVER_FOR_ELDERLY)
                            .careneedPersonAge(5 + i)
                            .preferredTimeInterval("09:00-12-00")
                            .caregivingFrequency((i * j) % 2 == 0 ? CaregivingFrequency.DAILY : CaregivingFrequency.WEEKLY)
                            .otherRequirements((i + j) % 3 == 0 ? "Other Requirements gentle for Job " + i : "no pets")
                            .datePosted(LocalDate.now())
                            .build());
                }

                for (int j = 1; j < 3; j++) {
                    jobApplicationRepo.save(JobApplication.builder()
                            .caregiver(caregiverRepo.getReferenceById((long) i))
                            .job(jobRepo.getReferenceById((long) i))
                            .dateApplied(LocalDate.now())
                            .build());
                }

            }
            for (int i = 1, j = UPPER_LIMIT + 1 - i; i <= UPPER_LIMIT; i++, j--) {
                Member member = memberRepo.getReferenceById((long) j);
                Caregiver caregiver = caregiverRepo.getReferenceById((long) i);
                appointmentRepo.save(Appointment.builder()
                        .caregiver(caregiver)
                        .member(member)
                        .appointmentDate(LocalDate.now())
                        .appointmentTime(LocalTime.now())
                        .workHours(i * j % 15)
                        .status((i * j) % 3 == 0 ? AppointmentStatus.CONFIRMED : AppointmentStatus.PENDING)
                        .memberContact("1234567")
                        .caregiverContact("09876543")
                        .build());
            }

            for (int i = 1; i <= UPPER_LIMIT; i++) {
                messageRepo.save(Message.builder()
                        .senderUser(userRepo.getReferenceById((long) i))
                        .receiverUser(userRepo.getReferenceById((long) (UPPER_LIMIT - i + 1)))
                        .messageText("message text " + i)
                        .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                        .build());
            }
        };
    }
}
