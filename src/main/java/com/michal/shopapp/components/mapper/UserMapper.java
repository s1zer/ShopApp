package com.michal.shopapp.components.mapper;

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements GenericMapper<User, UserDTO> {


    @Override
    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setBirthDate(userDTO.getBirthDate());

        return user;
    }


    @Override
    public UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(user.getBirthDate());

        return userDTO;
    }
}
