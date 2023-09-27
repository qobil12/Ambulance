package com.company.service;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.dto.ChangeStatusDTO;
import com.company.dto.PatientApplicationDTO;
import com.company.entity.Application;
import com.company.entity.PatientApplication;
import com.company.exceptions.ItemNotFoundException;
import com.company.mapper.ApplicationMapper;
import com.company.mapper.PatientApplicationMapper;
import com.company.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    final ApplicationRepository applicationRepository;
    final BrigadeRepository brigadeRepository;
    final RegionRepository regionRepository;
    final UserRepository userRepository;
    final PatientApplicationRepository patientApplicationRepository;
    final ApplicationMapper applicationMapper = ApplicationMapper.INSTANCE;
    final PatientApplicationMapper patientApplicationMapper = PatientApplicationMapper.INSTANCE;

    public ApplicationService(ApplicationRepository applicationRepository, BrigadeRepository brigadeRepository, RegionRepository regionRepository, UserRepository userRepository, PatientApplicationRepository patientApplicationRepository) {
        this.applicationRepository = applicationRepository;
        this.brigadeRepository = brigadeRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
        this.patientApplicationRepository = patientApplicationRepository;
    }


    public ApplicationInfoDTO createApplication(ApplicationDTO dto) {
        brigadeRepository.findById(dto.getBrigadeId()).orElseThrow(() -> new ItemNotFoundException("Brigade doesn't exists with this ID"));
        PatientApplication patientApplication = patientApplicationRepository.findById(dto.getPatientApplicationId()).orElseThrow(() -> new ItemNotFoundException("Patient doesn't exist with this ID "));
        regionRepository.findById(patientApplication.getRegionId()).orElseThrow(() -> new ItemNotFoundException("Region doesn't exist with this ID"));
        patientApplication.setIsAttached(true);

        return applicationMapper.toApplicationInfoDTO(applicationMapper.toEntity(dto, patientApplication));
    }

    public void changeStatus(ChangeStatusDTO dto) {
        applicationRepository.changeApplicationStatus(dto.getStatus(), dto.getId());
    }

    public List<ApplicationInfoDTO> getApplicationsListByStatus(Boolean status) {

        return applicationRepository.findAllByIsClosed(status)
                .stream()
                .map(applicationMapper::toApplicationInfoDTO).toList();
    }

    public List<ApplicationInfoDTO> getAllApplicationsList() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream().map(applicationMapper::toApplicationInfoDTO).toList();
    }

    public PatientApplicationDTO createPatientApplication(PatientApplicationDTO dto) {

        regionRepository.findById(dto.getRegionId()).orElseThrow(() -> new ItemNotFoundException("Region doesn't exist with this ID !"));
        userRepository.findById(dto.getUserId()).orElseThrow(() -> new ItemNotFoundException(" User doesn't exist whit this ID !"));

        return patientApplicationMapper.toPatientApplicationDTO(patientApplicationMapper.toPatientApplicationEntity(dto));
        //lat : 41.311117
        //long : 69.279761
    }

    public List<PatientApplication> getPatientApplications() {
        return patientApplicationRepository.findAllByIsAttached(false);
    }
}