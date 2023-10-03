package com.company.controller;

import com.company.dto.AuthDTO;

import com.company.dto.ProfileDTO;
import com.company.dto.UserDTO;
import com.company.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Operation(summary = "User registration API",description = "By this method user is registered.")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registration(@RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok().body(authService.registration(dto));
    }

    @Operation(summary = "Login", description="Method for login")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody @Valid AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
