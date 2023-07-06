package com.bikkadit.ecommerce.service.impl;

import com.bikkadit.ecommerce.dto.UserDto;
import com.bikkadit.ecommerce.entity.User;
import com.bikkadit.ecommerce.repository.UserRepository;
import com.bikkadit.ecommerce.service.UserService;
import com.bikkadit.ecommerce.utility.GlobalResources;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * @Author Monali
     * @apiNote create user
     * @param userDto
     * @return newDto
     */
    @Override
    public UserDto createUser(UserDto userDto) {

        logger.info("Request initiated for SerServiceImpl to create user ");
// generate unique id in string format
       String userId =  UUID.randomUUID().toString();
            userDto.setUserId(userId);

        //dto to entity

        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        //entity to dto

        UserDto newDto = entityToDto(savedUser);
        logger.info("Request completed foe UserServiceImpl to create user");
        return newDto;
    }


    /**
     * @Authoe Monali
     * @apiNote update user
     * @param userDto
     * @param userId
     * @return updated Dto
     */
    @Override
    public UserDto updatedUser(UserDto userDto, String userId) {
       User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with given id"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setImageName(userDto.getImageName());

       User updatedUser =  userRepository.save(user);
       UserDto updatedDto = entityToDto(updatedUser);
       return updatedDto ;
    }

    /**
     * @author Monali
     * @apiNote delete user
      * @param userId
     */
    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    /**
     * @author Monali
     * @apiNote get all user
      * @return list of dto
     */
    @Override
    public List<UserDto> getAllUser() {
       List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    /**
     * @author Monali
     * @apiNote get user by id
     * @param userId
     * @return user
     */
    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with given id"));

        return entityToDto(user);
    }

    /**
     * @author Monali
     * @apiNote get user by email
     * @param email
     * @return user
     */
    @Override
    public UserDto getUserByEmail(String email) {
       User user =  userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with emailID and password"));
       return entityToDto(user);
    }

    /**
     * @author Monali
     * @apiNote search user
     * @param keyword
     * @return dto list
     */
    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private User dtoToEntity(UserDto userDto) {

//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .gender(userDto.getGender())
//                .about(userDto.getAbout())
//                .imageName(userDto.getImageName())
//                .build();

        return mapper.map(userDto,User.class);

    }

    private UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .gender(savedUser.getGender())
//                .about(savedUser.getAbout())
//                .imageName(savedUser.getImageName())
//                .build();

        return mapper.map(savedUser,UserDto.class);

    }


}

