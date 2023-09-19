package com.company.controller;

import com.company.dto.ApplicationDTO;
import com.company.dto.ApplicationInfoDTO;
import com.company.dto.BrigadeDTO;
import com.company.dto.ChangeStatusDTO;
import com.company.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @Operation( summary = "Create Application", description = "By this method you can create an application .")
    @PostMapping(name = "/create")
    public ResponseEntity<String> createApplication(@RequestBody ApplicationDTO dto) {
        applicationService.createApplication(dto);
        return ResponseEntity.ok().body("Application succesfully created .");
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
}
