package com.michal.shopapp.components.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "shop_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String birthDate;
    private String firstName;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
