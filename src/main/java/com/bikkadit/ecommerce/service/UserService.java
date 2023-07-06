package com.bikkadit.ecommerce.service;

import com.bikkadit.ecommerce.dto.UserDto;

import java.util.List;


public interface UserService {

    //Create

    UserDto createUser(UserDto userDto);

    //Update

    UserDto updatedUser(UserDto userDto, String userId);

    //Delete

    void deleteUser(String userId);

    //Get All users

    List<UserDto> getAllUser();

    //Get single user by id

    UserDto getUserById(String userId);

    //Get single user by email

    UserDto getUserByEmail(String email);

    //Search user

    List<UserDto> searchUser(String keyword);


}

