package com.company.controller;

import com.company.dto.ChangeUserInfoDTO;
import com.company.dto.UserDTO;
import com.company.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "User registration API",description = "By this method user is registered.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> registration(@RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok().body(userService.registration(dto));
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "Change info",description = "By this method user can change all or necessary infos about himself")
    @PutMapping("/change_info_details")
    public ResponseEntity<UserDTO> changeInfo(@RequestBody @Valid ChangeUserInfoDTO userDto){
        return ResponseEntity.ok().body(userService.changeUserInfos(userDto));
    }
}
