package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Comment;
import com.alpdogan.catharsia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    //display where?
    @GetMapping
    public String displayComments (Model model) {

        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comment", comments);

        return "list-comments";

    }

    //likes and so on
    @GetMapping("/details")
    public String displayCommentDetails(@RequestParam("id") int id, Model model) {

        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);

        return "comment-details";

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

    @GetMapping("/update")
    public String displayCommentUpdateForm(@RequestParam("id") int id, Model model) {

        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);

        return "update-comment";

    }

    @PostMapping("/updateComment")
    public String updateComment(@ModelAttribute ("comment") Comment comment,
                                @RequestParam String text,
                                @RequestParam LocalDateTime createdAt
                                ) {

        commentService.updateCommentById(text, createdAt, comment);

        return "redirect:/comments";

    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") int id, Model model) {

        commentService.deleteCommentById(id);

        return "redirect:/comments";

    }

}


























