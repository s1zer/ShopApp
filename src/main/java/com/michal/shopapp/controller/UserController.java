package com.michal.shopapp.controller;

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.model.User;
import com.michal.shopapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

//    @PostMapping("")
//    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
//        if (userDTO.getId() == null) {
//
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id has to be null.");
//        }
//    }
}
