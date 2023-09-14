package com.company.service;

import com.company.dto.BrigadeDTO;
import com.company.entity.Brigade;
import com.company.repository.BrigadeRepository;
import com.company.repository.CarRepository;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrigadeService {
    @Autowired
    BrigadeRepository brigadeRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    CarRepository carRepository;

    public void createBrigade(BrigadeDTO dto) {
        Brigade brigade = new Brigade();
        brigade.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        brigade.setCar(carRepository.getByNumber(dto.getCarNumber()));
        brigade.setDoctor(doctorRepository.getByPhoneNumber(dto.getDoctorPhoneNumber()));

        brigadeRepository.save(brigade);
    }
}
