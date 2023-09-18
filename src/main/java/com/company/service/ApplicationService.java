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

    public ApplicationService(ApplicationRepository applicationRepository, BrigadeRepository brigadeRepository, RegionRepository regionRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.brigadeRepository = brigadeRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
    }

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

}