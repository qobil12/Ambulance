package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @Operation(summary = "Creates a region", description = "Creates a new region")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegionDTO> createRegion(@RequestBody @Valid RegionDTO dto) {
        return ResponseEntity.ok().body(regionService.createRegion(dto));
    }

    @Operation(summary = "Delete region", description = "By this method admin can delete region.")
    @DeleteMapping("/delete_region/{id}")
    public void deleteRegion(@PathVariable UUID id) {
        regionService.delete(id);
    }

    @Operation(summary = "Change region name", description = "By this method you can change region's name")
    @PutMapping("/change_name")
    public ResponseEntity<RegionDTO> changeRegionName(@RequestBody @Valid RegionDTO regionDTO) {
        return ResponseEntity.ok().body(regionService.changeName(regionDTO));
    }
}

