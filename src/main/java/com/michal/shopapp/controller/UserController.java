package com.michal.shopapp.controller;

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.service.UserService;
import com.michal.shopapp.utility.GlobalConstants;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    public UserDTO saveUser(@Valid @RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            return userService.saveDefinition(userDTO);
        } else {
            throw new ResponseStatusException(BAD_REQUEST, GlobalConstants.ID_HAS_TO_BE_NULL);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getDefinition(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public UserDTO putUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
        return userService.putDefinition(userDTO, id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public UserDTO patchUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return userService.patchDefinition(userDTO, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteUser(@PathVariable Long id) {
        userService.removeDefinition(id);
    }
}
