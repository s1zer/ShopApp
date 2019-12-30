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

        UserDTO userToSave = UnitTestUtils.createNewNoIDUserDTO();
        UserDTO expectedSavedUser = UnitTestUtils.createNewUserDTO();
        userToSave.setId(null);

        when(userService.saveDefinition(any(UserDTO.class))).thenReturn(expectedSavedUser);
        String userSavedJSON = objectMapper.writeValueAsString(userToSave);


        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSavedJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("ExampleFirstName-" + LocalDate.now())))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void shouldNotCreateWrongUser() throws Exception {
        UserDTO userToSave = UnitTestUtils.createNewNoIDUserDTO();
        userToSave.setId(1L);

        String userSavedJSON = objectMapper.writeValueAsString(userToSave);


        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSavedJSON))
                .andExpect(status().isBadRequest());
        verify(userService, never()).saveDefinition(any(UserDTO.class));
    }

    @Test
    void shouldPutNewUser() throws Exception {
        UserDTO userToPut = UnitTestUtils.createNewNoIDUserDTO();
        UserDTO expectedUser = UnitTestUtils.createNewUserDTO();
        Long userID = 1L;
        expectedUser.setId(userID);

        when(userService.putDefinition(userToPut, userID)).thenReturn(expectedUser);

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userToPut)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", equalTo("ExampleLastName-" + LocalDate.now())));
    }

    @Test
    void shouldPatchUser() throws Exception {
        UserDTO userToPatch = UnitTestUtils.createNewNoIDUserDTO();
        userToPatch.setFirstName("ChangedFirstName");
        UserDTO expectedUser = UnitTestUtils.createNewUserDTO();
        expectedUser.setFirstName(userToPatch.getFirstName());
        Long userID = 1L;
        expectedUser.setId(userID);

        when(userService.patchDefinition(userToPatch, userID)).thenReturn(expectedUser);

        mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userToPatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("ChangedFirstName")));
    }

    @Test
    void shouldDeleteUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).removeDefinition(anyLong());
    }

}
