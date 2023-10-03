package com.company.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.dto.BrigadeCarDTO;
import com.company.dto.BrigadeDTO;
import com.company.dto.BrigadeDoctorDTO;
import com.company.entity.Brigade;
import com.company.entity.Car;
import com.company.entity.Doctor;
import com.company.entity.Region;
import com.company.enums.CarModel;
import com.company.repository.BrigadeRepository;
import com.company.repository.CarRepository;
import com.company.repository.DoctorRepository;
import com.company.repository.RegionRepository;
import com.company.service.BrigadeService;

import java.util.ArrayList;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BrigadeController.class, BrigadeService.class})
@ExtendWith(SpringExtension.class)
class BrigadeControllerTest {
    @Autowired
    private BrigadeController brigadeController;

    @MockBean
    private BrigadeRepository brigadeRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private RegionRepository regionRepository;

    /**
     * Method under test: {@link BrigadeController#changeBrigadeCar(BrigadeCarDTO)}
     */
    @Test
    void testChangeBrigadeCar() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // Arrange
        Brigade brigade = mock(Brigade.class);
        doNothing().when(brigade).setCar(Mockito.<Car>any());
        Optional<Brigade> ofResult = Optional.of(brigade);

        Brigade brigade2 = new Brigade();
        brigade2.setCar(new Car());
        brigade2.setDoctor(new Doctor());
        brigade2.setId(UUID.randomUUID());
        brigade2.setIsBusy(true);
        brigade2.setRegion(new Region());

        Region region = new Region();
        region.setId(UUID.randomUUID());
        region.setName("Name");

        Car car = new Car();
        car.setBrigade(brigade2);
        car.setId(UUID.randomUUID());
        car.setModel(CarModel.MERCEDES);
        car.setNumber("42");
        car.setRegion(region);

        Brigade brigade3 = new Brigade();
        brigade3.setCar(new Car());
        brigade3.setDoctor(new Doctor());
        brigade3.setId(UUID.randomUUID());
        brigade3.setIsBusy(true);
        brigade3.setRegion(new Region());

        Region region2 = new Region();
        region2.setId(UUID.randomUUID());
        region2.setName("Name");

        Doctor doctor = new Doctor();
        doctor.setBrigade(brigade3);
        doctor.setId(UUID.randomUUID());
        doctor.setName("Name");
        doctor.setPhoneNumber("6625550144");
        doctor.setRegion(region2);
        doctor.setSurname("Doe");

        Region region3 = new Region();
        region3.setId(UUID.randomUUID());
        region3.setName("Name");

        Brigade brigade4 = new Brigade();
        brigade4.setCar(car);
        brigade4.setDoctor(doctor);
        brigade4.setId(UUID.randomUUID());
        brigade4.setIsBusy(true);
        brigade4.setRegion(region3);

        Region region4 = new Region();
        region4.setId(UUID.randomUUID());
        region4.setName("Name");

        Car car2 = new Car();
        car2.setBrigade(brigade4);
        UUID id = UUID.randomUUID();
        car2.setId(id);
        car2.setModel(CarModel.MERCEDES);
        car2.setNumber("42");
        car2.setRegion(region4);

        Brigade brigade5 = new Brigade();
        brigade5.setCar(new Car());
        brigade5.setDoctor(new Doctor());
        brigade5.setId(UUID.randomUUID());
        brigade5.setIsBusy(true);
        brigade5.setRegion(new Region());

        Region region5 = new Region();
        region5.setId(UUID.randomUUID());
        region5.setName("Name");

        Car car3 = new Car();
        car3.setBrigade(brigade5);
        car3.setId(UUID.randomUUID());
        car3.setModel(CarModel.MERCEDES);
        car3.setNumber("42");
        car3.setRegion(region5);

        Brigade brigade6 = new Brigade();
        brigade6.setCar(new Car());
        brigade6.setDoctor(new Doctor());
        brigade6.setId(UUID.randomUUID());
        brigade6.setIsBusy(true);
        brigade6.setRegion(new Region());

        Region region6 = new Region();
        region6.setId(UUID.randomUUID());
        region6.setName("Name");

        Doctor doctor2 = new Doctor();
        doctor2.setBrigade(brigade6);
        doctor2.setId(UUID.randomUUID());
        doctor2.setName("Name");
        doctor2.setPhoneNumber("6625550144");
        doctor2.setRegion(region6);
        doctor2.setSurname("Doe");

        Region region7 = new Region();
        region7.setId(UUID.randomUUID());
        region7.setName("Name");

        Brigade brigade7 = new Brigade();
        brigade7.setCar(car3);
        brigade7.setDoctor(doctor2);
        brigade7.setId(UUID.randomUUID());
        brigade7.setIsBusy(true);
        brigade7.setRegion(region7);

        Region region8 = new Region();
        region8.setId(UUID.randomUUID());
        region8.setName("Name");

        Doctor doctor3 = new Doctor();
        doctor3.setBrigade(brigade7);
        UUID id2 = UUID.randomUUID();
        doctor3.setId(id2);
        doctor3.setName("Name");
        doctor3.setPhoneNumber("6625550144");
        doctor3.setRegion(region8);
        doctor3.setSurname("Doe");

        Region region9 = new Region();
        UUID id3 = UUID.randomUUID();
        region9.setId(id3);
        region9.setName("Name");

        Brigade brigade8 = new Brigade();
        brigade8.setCar(car2);
        brigade8.setDoctor(doctor3);
        brigade8.setId(UUID.randomUUID());
        brigade8.setIsBusy(true);
        brigade8.setRegion(region9);
        BrigadeRepository brigadeRepository = mock(BrigadeRepository.class);
        when(brigadeRepository.save(Mockito.<Brigade>any())).thenReturn(brigade8);
        when(brigadeRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);

        Brigade brigade9 = new Brigade();
        brigade9.setCar(new Car());
        brigade9.setDoctor(new Doctor());
        brigade9.setId(UUID.randomUUID());
        brigade9.setIsBusy(true);
        brigade9.setRegion(new Region());

        Region region10 = new Region();
        region10.setId(UUID.randomUUID());
        region10.setName("Name");

        Car car4 = new Car();
        car4.setBrigade(brigade9);
        car4.setId(UUID.randomUUID());
        car4.setModel(CarModel.MERCEDES);
        car4.setNumber("42");
        car4.setRegion(region10);

        Brigade brigade10 = new Brigade();
        brigade10.setCar(new Car());
        brigade10.setDoctor(new Doctor());
        brigade10.setId(UUID.randomUUID());
        brigade10.setIsBusy(true);
        brigade10.setRegion(new Region());

        Region region11 = new Region();
        region11.setId(UUID.randomUUID());
        region11.setName("Name");

        Doctor doctor4 = new Doctor();
        doctor4.setBrigade(brigade10);
        doctor4.setId(UUID.randomUUID());
        doctor4.setName("Name");
        doctor4.setPhoneNumber("6625550144");
        doctor4.setRegion(region11);
        doctor4.setSurname("Doe");

        Region region12 = new Region();
        region12.setId(UUID.randomUUID());
        region12.setName("Name");

        Brigade brigade11 = new Brigade();
        brigade11.setCar(car4);
        brigade11.setDoctor(doctor4);
        brigade11.setId(UUID.randomUUID());
        brigade11.setIsBusy(true);
        brigade11.setRegion(region12);

        Region region13 = new Region();
        region13.setId(UUID.randomUUID());
        region13.setName("Name");

        Car car5 = new Car();
        car5.setBrigade(brigade11);
        car5.setId(UUID.randomUUID());
        car5.setModel(CarModel.MERCEDES);
        car5.setNumber("42");
        car5.setRegion(region13);
        Optional<Car> ofResult2 = Optional.of(car5);
        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult2);
        BrigadeController brigadeController = new BrigadeController(new BrigadeService(brigadeRepository,
                mock(RegionRepository.class), mock(DoctorRepository.class), carRepository));

        // Act
        ResponseEntity<BrigadeDTO> actualChangeBrigadeCarResult = brigadeController.changeBrigadeCar(new BrigadeCarDTO());

        // Assert
        assertTrue(actualChangeBrigadeCarResult.hasBody());
        assertTrue(actualChangeBrigadeCarResult.getHeaders().isEmpty());
        assertEquals(200, actualChangeBrigadeCarResult.getStatusCodeValue());
        BrigadeDTO body = actualChangeBrigadeCarResult.getBody();
        assertSame(id2, body.getDoctorId());
        assertSame(id, body.getCarId());
        assertSame(id3, body.getRegionId());
        verify(brigadeRepository).save(Mockito.<Brigade>any());
        verify(brigadeRepository).findById(Mockito.<UUID>any());
        verify(brigade).setCar(Mockito.<Car>any());
        verify(carRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link BrigadeController#changeBrigadeCar(BrigadeCarDTO)}
     */
    @Test
    void testChangeBrigadeCar2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // Arrange
        BrigadeService brigadeService = mock(BrigadeService.class);
        BrigadeDTO.BrigadeDTOBuilder builderResult = BrigadeDTO.builder();
        BrigadeDTO.BrigadeDTOBuilder carIdResult = builderResult.carId(UUID.randomUUID());
        BrigadeDTO.BrigadeDTOBuilder doctorIdResult = carIdResult.doctorId(UUID.randomUUID());
        when(brigadeService.changeBrigadeCar(Mockito.<BrigadeCarDTO>any()))
                .thenReturn(doctorIdResult.regionId(UUID.randomUUID()).build());
        BrigadeController brigadeController = new BrigadeController(brigadeService);

        // Act
        ResponseEntity<BrigadeDTO> actualChangeBrigadeCarResult = brigadeController.changeBrigadeCar(new BrigadeCarDTO());

        // Assert
        assertTrue(actualChangeBrigadeCarResult.hasBody());
        assertTrue(actualChangeBrigadeCarResult.getHeaders().isEmpty());
        assertEquals(200, actualChangeBrigadeCarResult.getStatusCodeValue());
        verify(brigadeService).changeBrigadeCar(Mockito.<BrigadeCarDTO>any());
    }

    /**
     * Method under test: {@link BrigadeController#changeBrigadeDoctor(BrigadeDoctorDTO)}
     */
    @Test
    void testChangeBrigadeDoctor() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // Arrange
        Brigade brigade = mock(Brigade.class);
        doNothing().when(brigade).setDoctor(Mockito.<Doctor>any());
        Optional<Brigade> ofResult = Optional.of(brigade);

        Brigade brigade2 = new Brigade();
        brigade2.setCar(new Car());
        brigade2.setDoctor(new Doctor());
        brigade2.setId(UUID.randomUUID());
        brigade2.setIsBusy(true);
        brigade2.setRegion(new Region());

        Region region = new Region();
        region.setId(UUID.randomUUID());
        region.setName("Name");

        Car car = new Car();
        car.setBrigade(brigade2);
        car.setId(UUID.randomUUID());
        car.setModel(CarModel.MERCEDES);
        car.setNumber("42");
        car.setRegion(region);

        Brigade brigade3 = new Brigade();
        brigade3.setCar(new Car());
        brigade3.setDoctor(new Doctor());
        brigade3.setId(UUID.randomUUID());
        brigade3.setIsBusy(true);
        brigade3.setRegion(new Region());

        Region region2 = new Region();
        region2.setId(UUID.randomUUID());
        region2.setName("Name");

        Doctor doctor = new Doctor();
        doctor.setBrigade(brigade3);
        doctor.setId(UUID.randomUUID());
        doctor.setName("Name");
        doctor.setPhoneNumber("6625550144");
        doctor.setRegion(region2);
        doctor.setSurname("Doe");

        Region region3 = new Region();
        region3.setId(UUID.randomUUID());
        region3.setName("Name");

        Brigade brigade4 = new Brigade();
        brigade4.setCar(car);
        brigade4.setDoctor(doctor);
        brigade4.setId(UUID.randomUUID());
        brigade4.setIsBusy(true);
        brigade4.setRegion(region3);

        Region region4 = new Region();
        region4.setId(UUID.randomUUID());
        region4.setName("Name");

        Car car2 = new Car();
        car2.setBrigade(brigade4);
        UUID id = UUID.randomUUID();
        car2.setId(id);
        car2.setModel(CarModel.MERCEDES);
        car2.setNumber("42");
        car2.setRegion(region4);

        Brigade brigade5 = new Brigade();
        brigade5.setCar(new Car());
        brigade5.setDoctor(new Doctor());
        brigade5.setId(UUID.randomUUID());
        brigade5.setIsBusy(true);
        brigade5.setRegion(new Region());

        Region region5 = new Region();
        region5.setId(UUID.randomUUID());
        region5.setName("Name");

        Car car3 = new Car();
        car3.setBrigade(brigade5);
        car3.setId(UUID.randomUUID());
        car3.setModel(CarModel.MERCEDES);
        car3.setNumber("42");
        car3.setRegion(region5);

        Brigade brigade6 = new Brigade();
        brigade6.setCar(new Car());
        brigade6.setDoctor(new Doctor());
        brigade6.setId(UUID.randomUUID());
        brigade6.setIsBusy(true);
        brigade6.setRegion(new Region());

        Region region6 = new Region();
        region6.setId(UUID.randomUUID());
        region6.setName("Name");

        Doctor doctor2 = new Doctor();
        doctor2.setBrigade(brigade6);
        doctor2.setId(UUID.randomUUID());
        doctor2.setName("Name");
        doctor2.setPhoneNumber("6625550144");
        doctor2.setRegion(region6);
        doctor2.setSurname("Doe");

        Region region7 = new Region();
        region7.setId(UUID.randomUUID());
        region7.setName("Name");

        Brigade brigade7 = new Brigade();
        brigade7.setCar(car3);
        brigade7.setDoctor(doctor2);
        brigade7.setId(UUID.randomUUID());
        brigade7.setIsBusy(true);
        brigade7.setRegion(region7);

        Region region8 = new Region();
        region8.setId(UUID.randomUUID());
        region8.setName("Name");

        Doctor doctor3 = new Doctor();
        doctor3.setBrigade(brigade7);
        UUID id2 = UUID.randomUUID();
        doctor3.setId(id2);
        doctor3.setName("Name");
        doctor3.setPhoneNumber("6625550144");
        doctor3.setRegion(region8);
        doctor3.setSurname("Doe");

        Region region9 = new Region();
        UUID id3 = UUID.randomUUID();
        region9.setId(id3);
        region9.setName("Name");

        Brigade brigade8 = new Brigade();
        brigade8.setCar(car2);
        brigade8.setDoctor(doctor3);
        brigade8.setId(UUID.randomUUID());
        brigade8.setIsBusy(true);
        brigade8.setRegion(region9);
        BrigadeRepository brigadeRepository = mock(BrigadeRepository.class);
        when(brigadeRepository.save(Mockito.<Brigade>any())).thenReturn(brigade8);
        when(brigadeRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);

        Brigade brigade9 = new Brigade();
        brigade9.setCar(new Car());
        brigade9.setDoctor(new Doctor());
        brigade9.setId(UUID.randomUUID());
        brigade9.setIsBusy(true);
        brigade9.setRegion(new Region());

        Region region10 = new Region();
        region10.setId(UUID.randomUUID());
        region10.setName("Name");

        Car car4 = new Car();
        car4.setBrigade(brigade9);
        car4.setId(UUID.randomUUID());
        car4.setModel(CarModel.MERCEDES);
        car4.setNumber("42");
        car4.setRegion(region10);

        Brigade brigade10 = new Brigade();
        brigade10.setCar(new Car());
        brigade10.setDoctor(new Doctor());
        brigade10.setId(UUID.randomUUID());
        brigade10.setIsBusy(true);
        brigade10.setRegion(new Region());

        Region region11 = new Region();
        region11.setId(UUID.randomUUID());
        region11.setName("Name");

        Doctor doctor4 = new Doctor();
        doctor4.setBrigade(brigade10);
        doctor4.setId(UUID.randomUUID());
        doctor4.setName("Name");
        doctor4.setPhoneNumber("6625550144");
        doctor4.setRegion(region11);
        doctor4.setSurname("Doe");

        Region region12 = new Region();
        region12.setId(UUID.randomUUID());
        region12.setName("Name");

        Brigade brigade11 = new Brigade();
        brigade11.setCar(car4);
        brigade11.setDoctor(doctor4);
        brigade11.setId(UUID.randomUUID());
        brigade11.setIsBusy(true);
        brigade11.setRegion(region12);

        Region region13 = new Region();
        region13.setId(UUID.randomUUID());
        region13.setName("Name");

        Doctor doctor5 = new Doctor();
        doctor5.setBrigade(brigade11);
        doctor5.setId(UUID.randomUUID());
        doctor5.setName("Name");
        doctor5.setPhoneNumber("6625550144");
        doctor5.setRegion(region13);
        doctor5.setSurname("Doe");
        Optional<Doctor> ofResult2 = Optional.of(doctor5);
        DoctorRepository doctorRepository = mock(DoctorRepository.class);
        when(doctorRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult2);
        BrigadeController brigadeController = new BrigadeController(new BrigadeService(brigadeRepository,
                mock(RegionRepository.class), doctorRepository, mock(CarRepository.class)));

        // Act
        ResponseEntity<BrigadeDTO> actualChangeBrigadeDoctorResult = brigadeController
                .changeBrigadeDoctor(new BrigadeDoctorDTO());

        // Assert
        assertTrue(actualChangeBrigadeDoctorResult.hasBody());
        assertTrue(actualChangeBrigadeDoctorResult.getHeaders().isEmpty());
        assertEquals(200, actualChangeBrigadeDoctorResult.getStatusCodeValue());
        BrigadeDTO body = actualChangeBrigadeDoctorResult.getBody();
        assertSame(id2, body.getDoctorId());
        assertSame(id, body.getCarId());
        assertSame(id3, body.getRegionId());
        verify(brigadeRepository).save(Mockito.<Brigade>any());
        verify(brigadeRepository).findById(Mockito.<UUID>any());
        verify(brigade).setDoctor(Mockito.<Doctor>any());
        verify(doctorRepository).findById(Mockito.<UUID>any());
    }

    /**
     * Method under test: {@link BrigadeController#changeBrigadeDoctor(BrigadeDoctorDTO)}
     */
    @Test
    void testChangeBrigadeDoctor2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // Arrange
        BrigadeService brigadeService = mock(BrigadeService.class);
        BrigadeDTO.BrigadeDTOBuilder builderResult = BrigadeDTO.builder();
        BrigadeDTO.BrigadeDTOBuilder carIdResult = builderResult.carId(UUID.randomUUID());
        BrigadeDTO.BrigadeDTOBuilder doctorIdResult = carIdResult.doctorId(UUID.randomUUID());
        when(brigadeService.changeBrigadeDoctor(Mockito.<BrigadeDoctorDTO>any()))
                .thenReturn(doctorIdResult.regionId(UUID.randomUUID()).build());
        BrigadeController brigadeController = new BrigadeController(brigadeService);

        // Act
        ResponseEntity<BrigadeDTO> actualChangeBrigadeDoctorResult = brigadeController
                .changeBrigadeDoctor(new BrigadeDoctorDTO());

        // Assert
        assertTrue(actualChangeBrigadeDoctorResult.hasBody());
        assertTrue(actualChangeBrigadeDoctorResult.getHeaders().isEmpty());
        assertEquals(200, actualChangeBrigadeDoctorResult.getStatusCodeValue());
        verify(brigadeService).changeBrigadeDoctor(Mockito.<BrigadeDoctorDTO>any());
    }

    /**
     * Method under test: {@link BrigadeController#deleteById(UUID)}
     */
    @Test
    void testDeleteById() throws Exception {
        // Arrange
        Car car = new Car();
        car.setBrigade(new Brigade());
        car.setId(UUID.randomUUID());
        car.setModel(CarModel.MERCEDES);
        car.setNumber("42");
        car.setRegion(new Region());

        Doctor doctor = new Doctor();
        doctor.setBrigade(new Brigade());
        doctor.setId(UUID.randomUUID());
        doctor.setName("Name");
        doctor.setPhoneNumber("6625550144");
        doctor.setRegion(new Region());
        doctor.setSurname("Doe");

        Region region = new Region();
        region.setId(UUID.randomUUID());
        region.setName("Name");

        Brigade brigade = new Brigade();
        brigade.setCar(car);
        brigade.setDoctor(doctor);
        brigade.setId(UUID.randomUUID());
        brigade.setIsBusy(true);
        brigade.setRegion(region);

        Region region2 = new Region();
        region2.setId(UUID.randomUUID());
        region2.setName("Name");

        Car car2 = new Car();
        car2.setBrigade(brigade);
        car2.setId(UUID.randomUUID());
        car2.setModel(CarModel.MERCEDES);
        car2.setNumber("42");
        car2.setRegion(region2);

        Car car3 = new Car();
        car3.setBrigade(new Brigade());
        car3.setId(UUID.randomUUID());
        car3.setModel(CarModel.MERCEDES);
        car3.setNumber("42");
        car3.setRegion(new Region());

        Doctor doctor2 = new Doctor();
        doctor2.setBrigade(new Brigade());
        doctor2.setId(UUID.randomUUID());
        doctor2.setName("Name");
        doctor2.setPhoneNumber("6625550144");
        doctor2.setRegion(new Region());
        doctor2.setSurname("Doe");

        Region region3 = new Region();
        region3.setId(UUID.randomUUID());
        region3.setName("Name");

        Brigade brigade2 = new Brigade();
        brigade2.setCar(car3);
        brigade2.setDoctor(doctor2);
        brigade2.setId(UUID.randomUUID());
        brigade2.setIsBusy(true);
        brigade2.setRegion(region3);

        Region region4 = new Region();
        region4.setId(UUID.randomUUID());
        region4.setName("Name");

        Doctor doctor3 = new Doctor();
        doctor3.setBrigade(brigade2);
        doctor3.setId(UUID.randomUUID());
        doctor3.setName("Name");
        doctor3.setPhoneNumber("6625550144");
        doctor3.setRegion(region4);
        doctor3.setSurname("Doe");

        Region region5 = new Region();
        region5.setId(UUID.randomUUID());
        region5.setName("Name");

        Brigade brigade3 = new Brigade();
        brigade3.setCar(car2);
        brigade3.setDoctor(doctor3);
        brigade3.setId(UUID.randomUUID());
        brigade3.setIsBusy(true);
        brigade3.setRegion(region5);
        Optional<Brigade> ofResult = Optional.of(brigade3);
        doNothing().when(brigadeRepository).delete(Mockito.<Brigade>any());
        when(brigadeRepository.findById(Mockito.<UUID>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/brigade/delete_by_id/{id}",
                UUID.randomUUID());

        // Act and Assert
        MockMvcBuilders.standaloneSetup(brigadeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link BrigadeController#getAllBrigadesListByIsBusyField(Boolean)}
     */
    @Test
    void testGetAllBrigadesListByIsBusyField() throws Exception {
        // Arrange
        when(brigadeRepository.findAllByIsBusy(Mockito.<Boolean>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/brigade/get_list_by_busy/{isBusy}", true);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(brigadeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BrigadeController#getAllList()}
     */
    @Test
    void testGetAllList() throws Exception {
        // Arrange
        when(brigadeRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/brigade/get_allist");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(brigadeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

