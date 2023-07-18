package com.bikkadit.ecommerce.dto;

import com.bikkadit.ecommerce.validate.ImageNameValid;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min=3, max = 15,message = "Invalid User name !!")
    private String name;

//    @Email(message = "Invalid User email !!")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-z]{2,3}$", message = "Invalid email")
    @NotBlank(message = "Email is required !!")
    private String email;

    @NotBlank(message = "Password is required !!")
    private String password;

    @Size(min = 4,max = 6,message = "Invalid gender !!")
    private String gender;

    @NotBlank(message = "Write something about yourself !!")
    private String about;

    @ImageNameValid()
    private String imageName;




}
