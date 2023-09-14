package com.company.controller;

import com.company.dto.UserDTO;
import com.company.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/adm/registration")
    private ResponseEntity<String> registration(@RequestBody @Valid UserDTO dto) {
        String registration = userService.registration(dto);
        return ResponseEntity.ok().body(registration);
    }


}
