package com.company.controller;

import com.company.dto.DoctorDTO;
import com.company.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(name = "/create")
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDTO dto){
        doctorService.createDoctor(dto);
        return ResponseEntity.ok().body("Doctor succesfully added !");
    }

}
