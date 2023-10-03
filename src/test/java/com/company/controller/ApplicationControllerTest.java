package com.company.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.dto.ChangeStatusDTO;
import com.company.dto.PatientApplicationDTO;
import com.company.entity.Region;
import com.company.repository.ApplicationRepository;
import com.company.repository.BrigadeRepository;
import com.company.repository.PatientApplicationRepository;
import com.company.repository.RegionRepository;
import com.company.repository.UserRepository;
import com.company.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ApplicationController.class, ApplicationService.class})
@ExtendWith(SpringExtension.class)
class ApplicationControllerTest {
    @Autowired
    private ApplicationController applicationController;

    @MockBean
    private ApplicationRepository applicationRepository;

    @MockBean
    private BrigadeRepository brigadeRepository;

    @MockBean
    private PatientApplicationRepository patientApplicationRepository;

    @MockBean
    private RegionRepository regionRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ApplicationController#createApplication(ApplicationDTO)}
     */
    @Test
    void testCreateApplication() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Handler dispatch failed: java.lang.NoSuchMethodError: 'java.lang.String com.company.entity.Application.getFullAddress()'
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.NoSuchMethodError: 'java.lang.String com.company.entity.Application.getFullAddress()'
        //       at com.company.mapper.ApplicationMapperImpl.toApplicationInfoDTO(ApplicationMapperImpl.java:48)
        //       at com.company.service.ApplicationService.createApplication(ApplicationService.java:46)
        //       at com.company.controller.ApplicationController.createApplication(ApplicationController.java:23)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ApplicationService applicationService = mock(ApplicationService.class);
        ApplicationInfoDTO.ApplicationInfoDTOBuilder builderResult = ApplicationInfoDTO.builder();
        ApplicationInfoDTO.ApplicationInfoDTOBuilder brigadeIdResult = builderResult.brigadeId(UUID.randomUUID());
        ApplicationInfoDTO.ApplicationInfoDTOBuilder patientFullNameResult = brigadeIdResult
                .createdDate(LocalDate.of(1970, 1, 1).atStartOfDay())
                .fullAddress("42 Main St")
                .patientFullName("Dr Jane Doe");
        when(applicationService.createApplication(Mockito.<ApplicationDTO>any()))
                .thenReturn(patientFullNameResult.regionId(UUID.randomUUID()).build());
        ApplicationController applicationController = new ApplicationController(applicationService);

        // Act
        ResponseEntity<ApplicationInfoDTO> actualCreateApplicationResult = applicationController
                .createApplication(new ApplicationDTO());

        // Assert
        assertTrue(actualCreateApplicationResult.hasBody());
        assertTrue(actualCreateApplicationResult.getHeaders().isEmpty());
        assertEquals(200, actualCreateApplicationResult.getStatusCodeValue());
        verify(applicationService).createApplication(Mockito.<ApplicationDTO>any());
    }

    /**
     * Method under test: {@link ApplicationController#changeApplicationStatus(ChangeStatusDTO)}
     */
    @Test
    void testChangeApplicationStatus() throws Exception {
        // Arrange
        doNothing().when(applicationRepository).changeApplicationStatus(Mockito.<Boolean>any(), Mockito.<UUID>any());

        ChangeStatusDTO changeStatusDTO = new ChangeStatusDTO();
        changeStatusDTO.setId(UUID.randomUUID());
        changeStatusDTO.setStatus(true);
        String content = (new ObjectMapper()).writeValueAsString(changeStatusDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/application/change_status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Application status changed intotrue"));
    }

    /**
     * Method under test: {@link ApplicationController#getListOfPatientApplications()}
     */
    @Test
    void testGetListOfPatientApplications() throws Exception {
        // Arrange
        when(patientApplicationRepository.findAllByIsAttached(anyBoolean())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/application/get_patient_applications");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ApplicationController#patient_application(PatientApplicationDTO)}
     */
    @Test
    void testPatient_application() throws Exception {
        // Arrange
        Region region = new Region();
        region.setId(UUID.randomUUID());
        region.setName("Name");
        Optional<Region> ofResult = Optional.of(region);
        when(regionRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/v1/application/patient_application");
        postResult.characterEncoding("https://example.org/example");

        PatientApplicationDTO patientApplicationDTO = new PatientApplicationDTO();
        patientApplicationDTO.setAddress("42 Main St");
        patientApplicationDTO.setIllness("Illness");
        patientApplicationDTO.setLatitude(1L);
        patientApplicationDTO.setLongitude(1L);
        patientApplicationDTO.setRegionId(UUID.randomUUID());
        patientApplicationDTO.setUserId(UUID.randomUUID());
        String content = (new ObjectMapper()).writeValueAsString(patientApplicationDTO);
        MockHttpServletRequestBuilder requestBuilder = postResult.contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    /**
     * Method under test: {@link ApplicationController#_applicationList()}
     */
    @Test
    void test_applicationList() throws Exception {
        // Arrange
        when(applicationRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/application/list/get_all_applications");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ApplicationController#_getAllByStatus(Boolean)}
     */
    @Test
    void test_getAllByStatus() throws Exception {
        // Arrange
        when(applicationRepository.findAllByIsClosed(Mockito.<Boolean>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/application/list/getByStatus/{status}", true);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(applicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

