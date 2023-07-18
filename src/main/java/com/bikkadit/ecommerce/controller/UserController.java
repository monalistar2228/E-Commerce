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

import javax.validation.Valid;
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
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){

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
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserDto userDto){

        logger.info("Request initiated for user service to update user with user id {}",userId);
        UserDto updatedUser = this.userService.updatedUser(userDto, userId);
        logger.info("Request completed for user service to update user with user id {}",userId);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
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

        logger.info("Request initiated for user service to delete user with userId {}",userId);
        userService.deleteUser(userId);
        ApiResponse message = ApiResponse.builder().message("User deleted Successfully !!").success(true).status(HttpStatus.OK).build();
        logger.info("Request completed for user service to delete user with userId {}",userId);
        return new ResponseEntity <ApiResponse>(message,HttpStatus.OK);

    }
    // get All user

    /**
     * @author Monali
     * @apiNote get all users
     * @return List of all Users
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){

        logger.info("Request initiated for user service to get all users");

        List<UserDto> allUsers = userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);

        logger.info("Request Completed for user service to get all users");
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
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
        logger.info("Request initiated for user service to get user with id {}",userId);
        UserDto userDto = userService.getUserById(userId);
        logger.info("Request completed for user service to get user with id {}",userId);
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
