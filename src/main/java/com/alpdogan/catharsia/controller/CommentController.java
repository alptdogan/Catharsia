package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Comment;
import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @GetMapping
    public String displayComments (Model model) {

        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comment", comments);

        return "list-comments";

    }

    @GetMapping("/newComment")
    public String displayCommentForm (Model model) {

        Comment comment = new Comment();
        model.addAttribute("comment", comment);

        return "new-comment";

    }

    @PostMapping("/addComment")
    public String createComment (@ModelAttribute("comment") Comment comment) {

        commentService.createComment(comment);

        return "redirect:/comments";

    }

    @GetMapping("/updateComment")
    public String displayCommentUpdateForm(@RequestParam("id") int id, Model model) {

        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);

        return "new-comment";

    }



}


























