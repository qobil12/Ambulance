package com.company.repository;

import com.company.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, String> {
    Doctor getByPhoneNumber(String nubmer);

    List<Doctor> findAllByBrigadeNull();
}
