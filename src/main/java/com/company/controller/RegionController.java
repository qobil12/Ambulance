package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/region")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
    @Operation(summary = "Creates a region",description = "Creates a new region")
    @PostMapping(name = "/create")
    private ResponseEntity<String> createRegion(@RequestBody RegionDTO dto){
        regionService.createRegion(dto);
        return ResponseEntity.ok().body("Region successfully created !");
    }

}

