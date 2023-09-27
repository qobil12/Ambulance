package com.company.controller;

import com.company.dto.*;
import com.company.entity.PatientApplication;
import com.company.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application/")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @Operation( summary = "Create Application", description = "By this method you can create an application .")
    @PostMapping("/create")
    public ResponseEntity<ApplicationInfoDTO> createApplication(@RequestBody ApplicationDTO dto) {

        return ResponseEntity.ok().body(applicationService.createApplication(dto));
    }

    @Operation(summary = "Change Application's status", description = "By this method you can change applications status when it closed .")
    @PutMapping( "/change_status")
    public ResponseEntity<String> changeApplicationStatus(@RequestBody ChangeStatusDTO dto) {
        applicationService.changeStatus(dto);
        return ResponseEntity.ok().body("Application status changed into" + dto.getStatus());
    }

    @Operation(summary = "Get All Applications' List", description = "You can all applications' list by this method .")
    @GetMapping("/list/get_all_applications")
    public ResponseEntity<List<ApplicationInfoDTO>> _applicationList() {
        return ResponseEntity.ok(applicationService.getAllApplicationsList());
    }

    @Operation(summary = "Get applications by status", description = "You can get applications' list by their status (Closed or not)")
    @GetMapping(  "/list/getByStatus/{status}")
    public ResponseEntity<List<ApplicationInfoDTO>> _getAllByStatus(@PathVariable Boolean status) {
        return ResponseEntity.ok(applicationService.getApplicationsListByStatus(status));
    }

    @Operation(summary = "Patient application",description = "By this method patient gives application ")
    @PostMapping("/patient_application")
    public ResponseEntity<PatientApplicationDTO> patient_application(@RequestBody PatientApplicationDTO dto){
               return ResponseEntity.ok().body(applicationService.createPatientApplication(dto));
    }
    @Operation(summary = "Get non attached applications' list",description = "By this method dispatcher can get list " +
            "of applicaitons non attached to brigade")
    @GetMapping("/get_patient_applications")
    public ResponseEntity<List<PatientApplication>> getListOfPatientApplications(){
        return ResponseEntity.ok().body(applicationService.getPatientApplications());
    }

}
