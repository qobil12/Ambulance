package com.company.controller;

import com.company.dto.BrigadeCarDTO;
import com.company.dto.BrigadeDTO;
import com.company.dto.BrigadeDoctorDTO;
import com.company.service.BrigadeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/brigade/")
public class BrigadeController {
    private final BrigadeService brigadeService;

    public BrigadeController(BrigadeService brigadeService) {
        this.brigadeService = brigadeService;
    }

    @Operation(summary = "Creates brigade",description = "By this method you can create a brigade.")
    @PostMapping("/create")
    private ResponseEntity<BrigadeDTO> createBrigade(@RequestBody BrigadeDTO dto){
        return ResponseEntity.ok().body(brigadeService.createBrigade(dto));
    }

    @Operation(summary = "Get list by isBusy field ", description = "By this method you can get list of all brigades by their isBusy field ")
    @GetMapping("/get_list_by_busy/{isBusy}")
    public ResponseEntity<List<BrigadeDTO>> getAllBrigadesListByIsBusyField( @PathVariable Boolean isBusy){
        List<BrigadeDTO> allByIsBusyField = brigadeService.getAllByIsBusyField(isBusy);
        return ResponseEntity.ok().body(allByIsBusyField);
    }

    @Operation(summary = "Delete brigade by id",description = "By this method you can delete brigade by its id")
    @DeleteMapping("/delete_by_id/{id}")
    public void deleteById(@PathVariable UUID id){
        brigadeService.deleteById(id);
    }
    @Operation(summary = "Change brigade's car", description = "By this method you can change brigade's old car to new one.")
    @PutMapping("/change_car")
    public ResponseEntity<BrigadeDTO> changeBrigadeCar(@RequestBody BrigadeCarDTO dto){
        return ResponseEntity.ok(brigadeService.changeBrigadeCar(dto));
    }

    @Operation(summary = "Change brigade's doctor", description = "By this method you can change brigade's old doctor to new one.")
    @PutMapping("/change_doctor")
    public ResponseEntity<BrigadeDTO> changeBrigadeDoctor(@RequestBody BrigadeDoctorDTO dto){
        return ResponseEntity.ok(brigadeService.changeBrigadeDoctor(dto));
    }

    @Operation(summary = "Get All list  of brigades",description = "By this method you can get all list of brigade")
    @GetMapping("/get_allist")
    public ResponseEntity<List<BrigadeDTO>> getAllList(){
        return ResponseEntity.ok().body(brigadeService.getAllList());
    }

}
