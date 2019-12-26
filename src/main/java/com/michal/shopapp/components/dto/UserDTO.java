package com.michal.shopapp.components.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory!")
    private String lastName;
    @NotBlank(message = "Email is mandatory!")
    private String email;
    @NotBlank(message = "Birth date is mandatory!")
    private String birthDate;
    private boolean isActive;

}
