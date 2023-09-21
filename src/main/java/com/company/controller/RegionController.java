package com.company.controller;

import com.company.dto.RegionChangeDTO;
import com.company.dto.RegionDTO;
import com.company.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/region")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
    @Operation(summary = "Creates a region",description = "Creates a new region")
    @PostMapping( "/create")
    public ResponseEntity<String> createRegion(@RequestBody RegionDTO dto){
        regionService.createRegion(dto);
        return ResponseEntity.ok().body("Region successfully created !");
    }
    @Operation(summary = "Delete region",description = "By this method admin can delete region.")
    @DeleteMapping("delete_region/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable String id){
        return ResponseEntity.ok().body(regionService.delete(id));
    }
    @Operation(summary = "Change region name",description = "By this method you can change region's name")
    @PutMapping("/change_name")
    public ResponseEntity<String> changeRegionName(@RequestBody RegionChangeDTO dto){
        return ResponseEntity.ok().body(regionService.changeName(dto));
    }
}

