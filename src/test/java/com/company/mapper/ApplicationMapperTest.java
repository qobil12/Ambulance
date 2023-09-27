package com.company.mapper;

import com.company.dto.ApplicationDTO;
import com.company.entity.Application;
import com.company.entity.PatientApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.UUID;

@SpringBootTest
class ApplicationMapperTest {
    ApplicationMapper applicationMapper = ApplicationMapper.INSTANCE;

    @Test
    void toApplicationEntity() {

        PodamFactory factory = new PodamFactoryImpl();
        ApplicationDTO applicationDTO = factory.manufacturePojo(ApplicationDTO.class);
        PatientApplication patientApplication = factory.manufacturePojo(PatientApplication.class);
        patientApplication.setRegionId(UUID.randomUUID());
        patientApplication.setUserId(UUID.fromString("5d6b8bac-5c53-11ee-8c99-0242ac120002"));


        Application applicationEntity = applicationMapper.toEntity(applicationDTO, patientApplication);
        System.out.println(applicationDTO);
        System.out.println(patientApplication);
        System.out.println(applicationEntity);
    }
}