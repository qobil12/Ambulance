package com.company.repository;

import com.company.entity.PatientApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientApplicationRepository extends CrudRepository<PatientApplication,String> {
PatientApplication getById(String id);

    List<PatientApplication> findAllByIsAttached(boolean b);
}
