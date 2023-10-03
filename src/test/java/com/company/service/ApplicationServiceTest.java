package com.company.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.company.dto.ApplicationDTO;
import com.company.exceptions.ItemNotFoundException;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ApplicationServiceTest {
    @Autowired
    private ApplicationService applicationService;

    /**
     * Method under test: {@link ApplicationService#createApplication(ApplicationDTO)}
     */
    @Test
    void testCreateApplication() {
        // Arrange
        ApplicationDTO.ApplicationDTOBuilder builderResult = ApplicationDTO.builder();
        ApplicationDTO.ApplicationDTOBuilder brigadeIdResult = builderResult.brigadeId(UUID.randomUUID());

        // Act and Assert
        assertThrows(ItemNotFoundException.class,
                () -> applicationService.createApplication(brigadeIdResult.patientApplicationId(UUID.randomUUID()).build()));
    }

    /**
     * Method under test: {@link ApplicationService#createApplication(ApplicationDTO)}
     */
    @Test
    void testCreateApplication2() {
        // Arrange
        ApplicationDTO dto = new ApplicationDTO();
        dto.setBrigadeId(UUID.randomUUID());

        // Act and Assert
        assertThrows(ItemNotFoundException.class, () -> applicationService.createApplication(dto));
    }

    /**
     * Method under test: {@link ApplicationService#getPatientApplications()}
     */
    @Test
    void testGetPatientApplications() {
        // Arrange, Act and Assert
        assertTrue(applicationService.getPatientApplications().isEmpty());
        assertTrue(applicationService.getAllApplicationsList().isEmpty());
    }
}

