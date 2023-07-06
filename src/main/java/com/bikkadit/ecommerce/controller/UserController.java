package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.dto.UserDto;
import com.bikkadit.ecommerce.payloads.ApiResponse;
import com.bikkadit.ecommerce.service.UserService;
import com.bikkadit.ecommerce.utility.GlobalResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Create

    /**
     * @author Monali
     * @apiNote create user
     * @param  userDto
     * @return
     */
    @PostMapping(value = "/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        UserDto createUser = userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);

    }

    //Update

    /**
     * @Author Monali
     * @apiNote update User
     * @param userDto
     * @param userId
     * @return
     */
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId){

        UserDto updatedUser = this.userService.updatedUser(userDto, userId);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.CREATED);
    }

    //Delete

    /**
     * @author Monali
     * @apiNote Delete User by Id
     * @param userId
     * @returnafd
     */
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("Delete user successfully", true),HttpStatus.OK);

    }
    // get All user

    /**
     * @author Monali
     * @apiNote get all users
     * @return
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> allUsers = userService.getAllUser();

        return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
    }

    // get single user by id

    /**
     * @author Monali
     * @apiNote get single user by id
     * @param userId
     * @return
     */
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String  userId)
    {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);

    }


}
