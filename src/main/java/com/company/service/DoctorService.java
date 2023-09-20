package com.company.service;

import com.company.dto.BrigadeDTO;
import com.company.dto.DoctorDTO;
import com.company.entity.Brigade;
import com.company.entity.Car;
import com.company.entity.Doctor;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository repository;
    private final RegionRepository regionRepository;

    public DoctorService(DoctorRepository repository, RegionRepository regionRepository) {
        this.repository = repository;
        this.regionRepository = regionRepository;
    }

    public void createDoctor(DoctorDTO dto) {
        Doctor doctor=new Doctor();
        doctor.setName(dto.getName());
        doctor.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        doctor.setSurname(dto.getSurname());
        doctor.setPhoneNumber(dto.getPhoneNumber());

        repository.save(doctor);
    }

    public List<DoctorDTO> getAllFree() {
        return repository.findAllByBrigadeNull()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO toDTO(Doctor doctor){
        return DoctorDTO.builder().surname(doctor.getSurname())
                .regionName(doctor.getRegion().getName())
                .phoneNumber(doctor.getPhoneNumber())
                .name(doctor.getName()).build();
    }
}
