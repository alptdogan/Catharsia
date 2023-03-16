package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.dto.request.UpdateUserBioRequestDto;
import com.alpdogan.catharsia.dto.response.UserResponseDto;
import com.alpdogan.catharsia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("findAllUsers")
    public ResponseEntity<List<UserResponseDto>> findAllUsers()
    {
        List<UserResponseDto> userResponseDtos = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserBioRequestDto updateUserBioRequestDto) {

        String updateUserDescription = userService.updateUserBio(updateUserBioRequestDto);

        return new ResponseEntity<>(updateUserDescription, HttpStatus.OK);

    }


}
