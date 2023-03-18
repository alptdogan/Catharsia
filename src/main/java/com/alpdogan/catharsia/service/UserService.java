package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.dto.request.UpdateUserBioRequestDto;
import com.alpdogan.catharsia.dto.response.UserResponseDto;
import com.alpdogan.catharsia.entity.User;
import com.alpdogan.catharsia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<UserResponseDto> getAllUsers() {

        Iterable<User> users = userRepository.findAll();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users) {
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public String updateUserBio(UpdateUserBioRequestDto updateUserBioRequestDto) {

        int idUserRequest = updateUserBioRequestDto.getId();
        String bioUserRequest = updateUserBioRequestDto.getBio();

        Optional<User> userOptional = userRepository.findById(idUserRequest);
        User user = userOptional.get();

        user.setBio(bioUserRequest);

        userRepository.save(user);

        return "Changes Saved Successfully.";
    }

    public String deleteUserById(Integer userId)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        userRepository.delete(user);

        return "User Has Been Deleted.";
    }

}
