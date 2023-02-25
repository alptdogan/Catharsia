package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Comment;
import com.alpdogan.catharsia.entity.Topic;
import com.alpdogan.catharsia.entity.User;
import com.alpdogan.catharsia.repository.RoleRepository;
import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.TopicService;
import com.alpdogan.catharsia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @Autowired
    CommentService commentService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public String displayUsers (Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("user", users);

        return "list-users";

    }

    @GetMapping("/details")
    public String displayUserDetails(@RequestParam("id") int id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "user-details";

    }

    @GetMapping("/update")
    public String displayUserUpdateForm(@RequestParam("id") int id, Model model) {

        User user = userService.getUserById(id);

        //userService.updateUserByEmail();

        //User user = new User();
        //List<Topic> topics = topicService.getAllTopics();
        //List<Comment> comments = commentService.getAllComments();

        model.addAttribute("user", user);
        //model.addAttribute("allTopics", topics);
        //model.addAttribute("allComments", comments);

        return "update-user";

    }

    @PostMapping("/updateUser")
    public String updateUser (@ModelAttribute ("user") User user,
                                    @RequestParam("email") String email //,
                                   //@RequestParam List<Long> categories
                                                            ) {

        userService.updateUserByEmail(email, user);

        return "redirect:/users";

    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id, Model model) {

        userService.deleteUserById(id);

        return "redirect:/users";

    }

}
















