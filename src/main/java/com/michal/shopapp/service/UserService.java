package com.michal.shopapp.service;

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.mapper.UserMapper;
import com.michal.shopapp.components.model.User;
import com.michal.shopapp.exceptions.MyResourceNotFoundException;
import com.michal.shopapp.repository.UserRepository;
import com.michal.shopapp.utility.GlobalConstants;
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

    public UserDTO findUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::convertToDto).
                        orElseThrow(() -> new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND));
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepository.save(userMapper.convertToEntity(userDTO));
        return userMapper.convertToDto(savedUser);
    }

    public UserDTO putUser(UserDTO userDTO, Long userID) {
        Optional<User> userToUpdate = userRepository.findById(userID);
        if (userToUpdate.isPresent()) {
            userDTO.setId(userID);
            User updatedUser = userRepository.save(userMapper.convertToEntity(userDTO));
            return userMapper.convertToDto(updatedUser);
        } else {
            throw new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND);
        }
    }

    public UserDTO patchUser(UserDTO userDTO, Long userID) {
        Optional<User> userToUpdate = userRepository.findById(userID);
        if (userToUpdate.isPresent()) {
            userToUpdate.ifPresent(u -> {
                if (userDTO.getFirstName() == null) userDTO.setFirstName(u.getFirstName());
                if (userDTO.getLastName() == null) userDTO.setLastName(u.getLastName());
                if (userDTO.getBirthDate() == null) userDTO.setBirthDate(u.getBirthDate());
                if (userDTO.getEmail() == null) userDTO.setEmail(u.getEmail());
            });

            userDTO.setId(userToUpdate.get().getId());
            User savedUser = userRepository.save(userMapper.convertToEntity(userDTO));
            return userMapper.convertToDto(savedUser);

        } else {
            throw new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND);
        }
    }

    public void removeUserByID(Long userID) {
        if (userRepository.existsById(userID)) {
            userRepository.deleteById(userID);
        } else {
            throw new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND);
        }
    }
}
