package com.company.controller;

import com.company.dto.ApplicationDTO;
import com.company.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping(name = "/create")
    private ResponseEntity<String> createApplication(@RequestBody ApplicationDTO dto){
        applicationService.createApplication(dto);
        return ResponseEntity.ok().body("Application succesfully created !");
    }
    @DeleteMapping(name = "/delete_status")
    private ResponseEntity<String> changeApplicationStatus(){

    }
}
