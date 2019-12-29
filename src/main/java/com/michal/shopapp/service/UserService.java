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
public class UserService implements GenericService<UserDTO> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getDefinition(Long id) {
        return userRepository.findById(id)
                .map(userMapper::convertToDto).
                        orElseThrow(() -> new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND));
    }

    @Override
    public UserDTO saveDefinition(UserDTO userDTO) {
        User savedUser = userRepository.save(userMapper.convertToEntity(userDTO));
        return userMapper.convertToDto(savedUser);
    }

    @Override
    public UserDTO putDefinition(UserDTO userDTO, Long id) {
        Optional<User> userToUpdate = userRepository.findById(id);
        if (userToUpdate.isPresent()) {
            userDTO.setId(id);
            User updatedUser = userRepository.save(userMapper.convertToEntity(userDTO));
            return userMapper.convertToDto(updatedUser);
        } else {
            throw new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND);
        }
    }

    @Override
    public UserDTO patchDefinition(UserDTO userDTO, Long id) {
        Optional<User> userToUpdate = userRepository.findById(id);
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

    @Override
    public void removeDefinition(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new MyResourceNotFoundException(GlobalConstants.USER_NOT_FOUND);
        }
    }
}
