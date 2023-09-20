package com.company.controller;

import com.company.dto.DoctorDTO;
import com.company.entity.Car;
import com.company.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDTO dto){
        doctorService.createDoctor(dto);
        return ResponseEntity.ok().body("Doctor succesfully added !");
    }
    @Operation(summary = "Get all free doctors",description = "By this method you can get list of all doctors that doesn't have brigade .")
    @GetMapping("/get_all_free")
    public ResponseEntity<List<DoctorDTO>> getAllFreeDoctors() {
        return ResponseEntity.ok().body(doctorService.getAllFree());
    }
}
