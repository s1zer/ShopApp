package com.michal.shopapp.service;
/*
 *@created 26/12/2019 - 14:13
 *@project shop-app
 *@author michal
 */

import com.michal.shopapp.UnitTestUtils;
import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.mapper.UserMapper;
import com.michal.shopapp.components.model.User;
import com.michal.shopapp.exceptions.MyResourceNotFoundException;
import com.michal.shopapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserServiceUnitTest {

    private UserRepository userMockRepository;
    private UserService userService;
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
        userMockRepository = mock(UserRepository.class);
        userService = new UserService(userMockRepository, userMapper);
    }

    @Test
    void shouldGetUserById() {
        User user = UnitTestUtils.createNewUser();
        given(userMockRepository.findById(1L)).willReturn(Optional.of(user));

        Optional<UserDTO> foundUser = Optional.of((userService.getDefinition(1L)));

        verify(userMockRepository, times(1)).findById(1L);
        assertThat(foundUser.get().getId(), equalTo(1L));
        assertThat(foundUser.get().getBirthDate(), equalTo(LocalDate.now().toString()));
    }

    @Test
    void shouldNotGetUserById() {
        given(userMockRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(MyResourceNotFoundException.class, () -> userService.getDefinition(anyLong()));
    }

    @Test
    void shouldSaveNewUser() {
        UserDTO userDTO = UnitTestUtils.createNewUserDTO();
        User user = UnitTestUtils.createNewUser();

        given(userMockRepository.save(any(User.class))).willReturn(user);

        UserDTO savedUser = userService.saveDefinition(userDTO);

        verify(userMockRepository, times(1)).save(any(User.class));
        assertThat(savedUser.getId(), equalTo(userDTO.getId()));
        assertThat(savedUser.getFirstName(), equalTo(userDTO.getFirstName()));
    }

    @Test
    void shouldPutNewUser() {
        UserDTO userDTO = UnitTestUtils.createNewUserDTO();
        userDTO.setFirstName("UpdatedFirstName");
        userDTO.setLastName("UpdatedLastName");
        User user = UnitTestUtils.createNewUser();

        given(userMockRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(userMockRepository.save(any(User.class))).willReturn(userMapper.convertToEntity(userDTO));

        UserDTO putUserDTO = userService.putDefinition(userDTO, 1L);

        verify(userMockRepository, times(1)).findById(user.getId());
        assertThat(putUserDTO.getId(), equalTo(user.getId()));
        assertThat(putUserDTO.getFirstName(), equalTo(userDTO.getFirstName()));
        assertThat(putUserDTO.getLastName(), equalTo(userDTO.getLastName()));
    }

    @Test
    void shouldNotPutNewUser() {
        UserDTO userDTO = UnitTestUtils.createNewUserDTO();
        given(userMockRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(MyResourceNotFoundException.class, () -> userService.putDefinition(userDTO, 1L));
        verify(userMockRepository, times(1)).findById(anyLong());
        verify(userMockRepository, times(0)).save(any(User.class));
    }

    @Test
    void shouldPatchUserWithNoChanges() {
        UserDTO userDTO = UnitTestUtils.createNewUserDTO();
        userDTO.setFirstName(null);
        userDTO.setLastName(null);
        userDTO.setBirthDate(null);
        userDTO.setEmail(null);

        User user = UnitTestUtils.createNewUser();

        given(userMockRepository.findById(1L)).willReturn(Optional.of(user));
        given(userMockRepository.save(any(User.class))).willReturn(new User());

        userService.patchDefinition(userDTO, 1L);

        verify(userMockRepository, times(1)).findById(anyLong());
        verify(userMockRepository, times(1)).save(any(User.class));

    }

    @Test
    void shouldPatchUserWithChanges() {
        UserDTO userDTO = UnitTestUtils.createNewUserDTO();
        userDTO.setFirstName("Update");
        userDTO.setLastName("Update");
        userDTO.setBirthDate("Update");
        userDTO.setEmail("Update");

        User user = UnitTestUtils.createNewUser();

        given(userMockRepository.findById(1L)).willReturn(Optional.of(user));
        given(userMockRepository.save(any(User.class))).willReturn(new User());

        userService.patchDefinition(userDTO, 1L);

        verify(userMockRepository, times(1)).findById(anyLong());
        verify(userMockRepository, times(1)).save(any(User.class));

    }

    @Test
    void shouldNotPatchUser() {
        UserDTO userDTO = UnitTestUtils.createNewUserDTO();

        given(userMockRepository.findById(1L)).willReturn(Optional.ofNullable(null));

        assertThrows(MyResourceNotFoundException.class, () -> userService.patchDefinition(userDTO, 1L));
    }

    @Test
    void shouldDeleteUser() {
        given(userMockRepository.existsById(anyLong())).willReturn(true);

        userService.removeDefinition(1L);

        verify(userMockRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldNotDeleteUser() {
        given(userMockRepository.existsById(anyLong())).willReturn(false);

        assertThrows(MyResourceNotFoundException.class, () -> userService.removeDefinition(1L));
    }

}
