package com.company.controller;

import com.company.dto.UserDTO;
import com.company.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "User registration API",description = "By this method user is registered.")
    @PostMapping("/adm/registration")
    private ResponseEntity<String> registration(@RequestBody @Valid UserDTO dto) {
        String registration = userService.registration(dto);
        return ResponseEntity.ok().body(registration);
    }
}
