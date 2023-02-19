package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.User;
import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.TopicService;
import com.alpdogan.catharsia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public String displayUsers (Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("user", users);

        return "list-users";

    }


}
















