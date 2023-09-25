package com.company.repository;

import com.company.entity.PatientApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientApplicationRepository extends JpaRepository<PatientApplication, UUID> {
    List<PatientApplication> findAllByIsAttached(boolean b);

}
