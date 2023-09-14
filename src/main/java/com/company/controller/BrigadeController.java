package com.company.controller;

import com.company.dto.BrigadeDTO;
import com.company.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/brigade")
public class BrigadeController {
    @Autowired
    private BrigadeService brigadeService;

    @PostMapping(name = "/create")
    private ResponseEntity<String> createBrigade(@RequestBody BrigadeDTO dto){
        brigadeService.createBrigade(dto);
        return ResponseEntity.ok().body("Brigade Succesfully created !");
    }
}
