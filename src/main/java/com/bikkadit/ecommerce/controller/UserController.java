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


    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //Create

    /**
     * @author Monali
     * @apiNote create user
     * @param  userDto
     * @return User
     */
    @PostMapping(value = "/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        logger.info("Request initiated for user service to create users");
        UserDto createUser = userService.createUser(userDto);
        logger.info("Request completed for user service to create users");
        return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);

    }

    //Update

    /**
     * @Author Monali
     * @apiNote update User
     * @param userDto
     * @param userId
     * @return updated user
     */
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId){

        logger.info("Request initiated for user service to update user");
        UserDto updatedUser = this.userService.updatedUser(userDto, userId);
        logger.info("Request completed for user service to update user");
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.CREATED);
    }

    //Delete

    /**
     * @author Monali
     * @apiNote Delete User by Id
     * @param userId
     * @return
     */
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){

        logger.info("Request initiated for user service to delete user");
        userService.deleteUser(userId);
        logger.info("Request completed for user service to delete user");
        return new ResponseEntity(new ApiResponse("Delete user successfully", true),HttpStatus.OK);

    }
    // get All user

    /**
     * @author Monali
     * @apiNote get all users
     * @return List of all Users
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        logger.info("Request initiated for user service to get all users");
        List<UserDto> allUsers = userService.getAllUser();

        logger.info("Request Completed for user service to get all users");
        return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
    }

    // get single user by id

    /**
     * @author Monali
     * @apiNote get single user by id
     * @param userId
     * @return User by id
     */
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String  userId)
    {
        logger.info("Request initiated for user service to get user with id");
        UserDto userDto = userService.getUserById(userId);
        logger.info("Request completed for user service to get user with id");
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);

    }

    /**
     * @author Monali
     * apiNote get user by email
     * @param email
     * @return User by email
     */
    @GetMapping("/email/{email}")
   ResponseEntity<UserDto>getUserByEmail(@PathVariable String email)
    {
        logger.info("Request initiated for user service to get user with email id {}",email);
       UserDto userDto1 =  this.userService.getUserByEmail(email);
        logger.info("Request Completed for user service to get user with email id {}",email);
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);
    }

}
