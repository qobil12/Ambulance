package com.company.service;

import com.company.dto.ApplicationDTO;
import com.company.entity.Application;
import com.company.entity.Brigade;
import com.company.repository.ApplicationRepository;
import com.company.repository.BrigadeRepository;
import com.company.repository.RegionRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    BrigadeRepository brigadeRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    UserRepository userRepository;

    public void createApplication(ApplicationDTO dto){
        Brigade brigade=brigadeRepository.getBrigadeById(dto.getBrigadeId());
        Application application=new Application();
        application.setBrigade(brigade);
        application.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        application.setCreated_date(LocalDateTime.now());
        application.setPatient(userRepository.getById(dto.getPatientId()));
        application.setFullAddress(dto.getFullAddress());
        brigade.setIsBusy(true);

        brigadeRepository.save(brigade);
        applicationRepository.save(application);
    }
}