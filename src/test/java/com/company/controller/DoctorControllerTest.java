package com.company.controller;

import static org.mockito.Mockito.mock;

import com.company.dto.DoctorDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DoctorControllerTest {
    /**
     * Method under test: {@link DoctorController#createDoctor(DoctorDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateDoctor() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        DoctorController doctorController = new DoctorController();
        doctorController.createDoctor(new DoctorDTO("us-east-2", "Name", "Doe", "6625550144"));
    }

    /**
     * Method under test: {@link DoctorController#createDoctor(DoctorDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateDoctor2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new DoctorController()).createDoctor(mock(DoctorDTO.class));
    }
}

