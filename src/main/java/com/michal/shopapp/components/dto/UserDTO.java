package com.michal.shopapp.components.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    private String birthDate;
    @NotBlank
    private boolean isActive;

}
