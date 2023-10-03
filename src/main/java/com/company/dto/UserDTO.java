package com.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @NotNull(message = "Name mustn't be null")
    @NotBlank
    private String name;
    @NotNull(message = "Surname mustn't be null")
    @NotBlank
    private String surname;
    @NotBlank
    @NotNull(message = "Number mustn't be null")
    @Pattern(regexp = "^\\+998\\d{9}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String password;
    @NotBlank
    @NotNull
    private String username;


}
