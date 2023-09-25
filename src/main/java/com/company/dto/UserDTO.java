package com.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID userId;
    @NotNull(message = "Name mustn't be null")
    @NotBlank
    private String name;
    @NotNull(message = "Surname mustn't be null")
    @NotBlank
    private String Surname;
    @NotBlank
    @NotNull(message = "Email mustn't be null")
    @Email(message = "Number must contains only with numbers.",regexp = "\\+998\\d{9}")
    private String phoneNumber;
}
