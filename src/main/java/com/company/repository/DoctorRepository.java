package com.company.repository;

import com.company.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Doctor getByPhoneNumber(String number);

    List<Doctor> findAllByBrigadeNull();

}
