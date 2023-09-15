package com.company.controller;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.dto.ChangeStatusDTO;
import com.company.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping(name = "/change_status")
    private ResponseEntity<String> changeApplicationStatus(@RequestBody ChangeStatusDTO dto){
        applicationService.changeStatus(dto);
      return   ResponseEntity.ok().body("Application status changed into" +dto.getStatus());
    }
    @GetMapping(name = "/get_all")
    private ResponseEntity<List<ApplicationInfoDTO>> getAllApplications(){
        return ResponseEntity.ok().body(applicationService.getAllApplicationsList());
    }
    @GetMapping(name = "/get_by_status")
    private ResponseEntity<List<ApplicationInfoDTO>> getAllApplicationsByStatus(@RequestParam Boolean status){
       return ResponseEntity.ok().body(applicationService.getApplicationsListByStatus(status));
    }

}
