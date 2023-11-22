package com.example.demo.repository;

import com.example.demo.model.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiverRepo extends JpaRepository<Caregiver, Long> {
}
