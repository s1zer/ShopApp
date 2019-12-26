package com.michal.shopapp.components.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "shop_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Birth date is mandatory")
    private String birthDate;
    private boolean isActive;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
