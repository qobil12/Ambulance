package com.company.service;

import com.company.dto.DoctorDTO;
import com.company.entity.Doctor;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository repository;
    @Autowired
    private RegionRepository regionRepository;

    public void createDoctor(DoctorDTO dto) {
        Doctor doctor=new Doctor();
        doctor.setName(dto.getName());
        doctor.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        doctor.setSurname(dto.getSurname());
        doctor.setPhoneNumber(dto.getPhoneNumber());

        repository.save(doctor);
    }
}
