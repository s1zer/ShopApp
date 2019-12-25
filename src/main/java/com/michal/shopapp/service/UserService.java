package com.michal.shopapp.service;

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.mapper.UserMapper;
import com.michal.shopapp.components.model.User;
import com.michal.shopapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<UserDTO> findUserById(Long userId) {
        return userRepository.findById(userId).map(userMapper::convertToDto);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(userMapper.convertToEntity(userDTO));
        return userMapper.convertToDto(savedUser);
    }

    public void removeUserByID(Long userID) {
        userRepository.deleteById(userID);
    }
}
