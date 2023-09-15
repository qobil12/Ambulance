package com.company.service;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.dto.ChangeStatusDTO;
import com.company.entity.Application;
import com.company.entity.Brigade;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.ApplicationRepository;
import com.company.repository.BrigadeRepository;
import com.company.repository.RegionRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public void createApplication(ApplicationDTO dto) {
        Brigade brigade = brigadeRepository.getBrigadeById(dto.getBrigadeId());
        Application application = new Application();
        application.setBrigade(brigade);
        application.setRegion(regionRepository.getRegionByName(dto.getRegionName().toUpperCase()));
        application.setCreated_date(LocalDateTime.now());
        application.setPatient(userRepository.getById(dto.getPatientId()));
        application.setFullAddress(dto.getFullAddress());
        brigade.setIsBusy(true);

        brigadeRepository.save(brigade);
        applicationRepository.save(application);

    }

    public void changeStatus(ChangeStatusDTO dto) {
        applicationRepository.changeApplicationStatus(dto.getStatus(), dto.getId());
    }

    public List<ApplicationInfoDTO> getApplicationsListByStatus(Boolean status) {
        List<Application> applications=applicationRepository.getApplicationByIsClosed(status);
        return getApplicationInfoDTOS(applications);
    }

    private List<ApplicationInfoDTO> getApplicationInfoDTOS(List<Application> applications) {
        if (applications.isEmpty()) {
            throw new ItemNotFoundException("Applications not found !");
        }

        List<ApplicationInfoDTO> dtoList = new LinkedList<>();

        applications.forEach(application -> {
                    ApplicationInfoDTO dto = toDTO(application);
                    dtoList.add(dto);
                }
        );
        return dtoList;
    }

    public List<ApplicationInfoDTO> getAllApplicationsList() {
        List<Application> all = applicationRepository.getAll();
        return getApplicationInfoDTOS(all);
    }

    public ApplicationInfoDTO toDTO(Application application) {
        ApplicationInfoDTO dto = new ApplicationInfoDTO();
        dto.setStatus(application.getIsClosed());
        dto.setCreatedDate(application.getCreated_date());
        dto.setBrigadeId(application.getBrigade().getId());
        dto.setRegionName(application.getRegion().getName());
        dto.setPatientFullName(application.getPatient().getName() + " " + application.getPatient().getSurname());
        dto.setFullAddress(application.getFullAddress());
        return dto;
    }

}