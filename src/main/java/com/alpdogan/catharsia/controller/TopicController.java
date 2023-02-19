package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Topic;
import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public String displayClients (Model model) {

        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);

        return "list-topics";

    }

}
