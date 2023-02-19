package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    CommentService commentService;



}
