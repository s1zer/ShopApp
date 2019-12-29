package com.michal.shopapp;
/*
 *@created 26/12/2019 - 14:21
 *@project shop-app
 *@author michal
 */

import com.michal.shopapp.components.dto.UserDTO;
import com.michal.shopapp.components.model.User;

import java.time.LocalDate;

public class UnitTestUtils {

    public static User createNewUser() {
        User user = new User();
        LocalDate today = LocalDate.now();
        user.setId(1L);
        user.setFirstName("ExampleFirstName-" + today);
        user.setLastName("ExampleLastName-" + today);
        user.setBirthDate(today.toString());
        user.setActive(false);
        user.setEmail("ExampleEmail-" + today);

        return user;
    }

    public static UserDTO createNewUserDTO() {
        UserDTO user = new UserDTO();
        LocalDate today = LocalDate.now();
        user.setId(1L);
        user.setFirstName("ExampleFirstName-" + today);
        user.setLastName("ExampleLastName-" + today);
        user.setBirthDate(today.toString());
        user.setActive(false);
        user.setEmail("ExampleEmail-" + today);

        return user;
    }
}
