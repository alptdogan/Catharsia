package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;



}
