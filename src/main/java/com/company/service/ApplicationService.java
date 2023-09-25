package com.company.service;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.dto.ChangeStatusDTO;
import com.company.dto.PatientApplicationDTO;
import com.company.entity.Application;
import com.company.entity.Brigade;
import com.company.entity.PatientApplication;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    final ApplicationRepository applicationRepository;
    final BrigadeRepository brigadeRepository;
    final RegionRepository regionRepository;
    final UserRepository userRepository;
    final PatientApplicationRepository patientApplicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository, BrigadeRepository brigadeRepository, RegionRepository regionRepository, UserRepository userRepository, PatientApplicationRepository patientApplicationRepository) {
        this.applicationRepository = applicationRepository;
        this.brigadeRepository = brigadeRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
        this.patientApplicationRepository = patientApplicationRepository;
    }

    public String  createApplication(ApplicationDTO dto) {
        Brigade brigade = brigadeRepository.getBrigadeById(dto.getBrigadeId());
        PatientApplication patientApplication=patientApplicationRepository.getById(dto.getPatientApplicationId());
        Application application = new Application();
        application.setBrigade(brigade);
        application.setRegion(regionRepository.getRegionByName(patientApplication.getRegionName().toUpperCase()));
        application.setCreated_date(LocalDateTime.now());
        application.setPatient(userRepository.getById(patientApplication.getUserId()));
        application.setFullAddress(patientApplication.getAddress());
        brigade.setBusy(true);
        patientApplication.setIsAttached(true);

        brigadeRepository.save(brigade);
        applicationRepository.save(application);
        patientApplicationRepository.save(patientApplication);
        return "Patient's application successfully joined with brigade !";

    }

    public void changeStatus(ChangeStatusDTO dto) {
        applicationRepository.changeApplicationStatus(dto.getStatus(), dto.getId());
    }

    public List<ApplicationInfoDTO> getApplicationsListByStatus(Boolean status) {

        return applicationRepository.findAllByIsClosed(status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    public List<ApplicationInfoDTO> getAllApplicationsList() {
        Iterable<Application> applications = applicationRepository.findAll();
        return getApplicationInfoDTOS(applications);
    }

    private List<ApplicationInfoDTO> getApplicationInfoDTOS(Iterable<Application> applications) {
        if (!applications.iterator().hasNext()) {
            throw new ItemNotFoundException("Applications not found!");
        }

        List<ApplicationInfoDTO> dtoList = new ArrayList<>();

        applications.forEach(application -> {
                    ApplicationInfoDTO dto = toDTO(application);
                    dtoList.add(dto);
                }
        );
        return dtoList;
    }


    private ApplicationInfoDTO toDTO(Application application) {

        return ApplicationInfoDTO.builder()
                .status(application.getIsClosed())
                .createdDate(application.getCreated_date())
                .brigadeId(application.getBrigade().getId())
                .regionName(application.getRegion().getName())
                .patientFullName(application.getPatient().getName() + " " + application.getPatient().getSurname())
                .fullAddress(application.getFullAddress())
                .build();
    }

    public PatientApplicationDTO createPatientApplication(PatientApplicationDTO dto) {

        PatientApplication patientApplication = new PatientApplication();
        patientApplication.setUserId(dto.getUserId());
        patientApplication.setAddress(dto.getAddress());
        patientApplication.setIllness(dto.getIllness());
        patientApplication.setLatitude(dto.getLatitude());
        patientApplication.setLongitude(dto.getLongitude());
        patientApplicationRepository.save(patientApplication);

        return dto;
        //lat : 41.311117
        //long : 69.279761
    }

    public List<PatientApplication> getPatientApplications() {
       return patientApplicationRepository.findAllByIsAttached(false);
    }
}