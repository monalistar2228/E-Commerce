package com.bikkadit.ecommerce.entity;

import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="users")
public class User {

    @Id
    private String userId;

    @Column(name = "user_name" )
    private String name;

    @Column(name="user_email")
    private String email;

    @Column(name="user_password",length = 15,nullable = true)
    private String password;

    private String gender;

    @Column(length = 1000)
    private String about;

    @Column(name = "user_image_name")
    private String imageName;


}

