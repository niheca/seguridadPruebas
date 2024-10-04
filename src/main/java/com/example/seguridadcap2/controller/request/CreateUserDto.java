package com.example.seguridadcap2.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {

    @Email
    private String email;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @NotBlank
    private String password;

    private Set<String> roles;
}
