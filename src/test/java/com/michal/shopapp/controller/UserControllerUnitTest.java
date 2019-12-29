package com.michal.shopapp.controller;
/*
 *@created 27/12/2019 - 17:30
 *@project shop-app
 *@author michal
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michal.shopapp.UnitTestUtils;
import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerUnitTest {

    private static final String BASE_URL = "/api/user/";
    private UserController userController;
    ObjectMapper objectMapper;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnUserByID() throws Exception {
        given(userService.getDefinition(anyLong())).willReturn(UnitTestUtils.createNewUserDTO());

        UserDTO foundUserDTO = userController.getUser(anyLong());

        verify(userService, times(1)).getDefinition(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("ExampleFirstName-" + LocalDate.now())));
    }

    @Test
    void shouldBeCreatedNewUser() throws Exception {

        UserDTO userToSave = UnitTestUtils.createNewUserDTO();
        UserDTO userSaved = new UserDTO();
        userSaved.setFirstName(userToSave.getFirstName());
        userSaved.setLastName(userToSave.getLastName());
        userSaved.setEmail(userToSave.getEmail());
        userSaved.setBirthDate(userToSave.getBirthDate());
        userSaved.setId(null);

//        given(userService.saveDefinition(userToSave)).willReturn(userSaved);
        when(userService.saveDefinition(any(UserDTO.class))).thenReturn(userSaved);
        String userSavedJSON = objectMapper.writeValueAsString(userSaved);


        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSavedJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("ExampleFirstName-" + LocalDate.now())))
                .andReturn().getResponse().getContentAsString();


    }

}
