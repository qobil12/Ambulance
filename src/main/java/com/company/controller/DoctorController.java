package com.company.controller;

import com.company.dto.ChangeDoctorInfoDTO;
import com.company.dto.DoctorDTO;
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
    @Operation(summary = "Creates a doctor entity.")
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
    @Operation(summary = "Delete doctor",description = "By this method admin can delete doctor.")
    @DeleteMapping("/delete_doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable String id){
        return ResponseEntity.ok().body(doctorService.delete(id));
    }
    @Operation(summary = "Change info about doctor",description = "By this method admin can change all or necessery info about doctor.")
    @PutMapping("/change_info")
    public ResponseEntity<String> changeInfo(@RequestBody ChangeDoctorInfoDTO dto){
            return ResponseEntity.ok().body(doctorService.changeDoctorInfo(dto));
    }
}
