package com.alpdogan.catharsia.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String bio;

    private int roleId;

    private int commentId;

}
