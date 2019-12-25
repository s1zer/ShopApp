package com.michal.shopapp.controller;

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.model.User;
import com.michal.shopapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            return userService.saveUser(userDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id has to be null.");
        }
    }

}
