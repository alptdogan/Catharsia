package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Like;
import com.alpdogan.catharsia.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    LikeService likeService;

    // do we need this?
    @GetMapping("/newLike")
    public String displayLikeForm (Model model) {

        Like like = new Like();
        model.addAttribute("like", like);

        return "new-like";

    }

    // must return +1, need to learn how to do it.
    @PostMapping("/addLike")
    public String createLike (@ModelAttribute("like") Like like) {

        likeService.createLike(like);

        return "redirect:/likes";

    }

    // how to update likes???
    @GetMapping("/updateLike")
    public String displayLikeUpdateForm (@RequestParam("id") int id, Model model) {

        Like like = likeService.getAllLikes().get(id); //.setId(?);
        model.addAttribute("like", like);

        return "new-like";

    }

    // do we delete likes by theirs IDs?
    @GetMapping("/deleteLike")
    public String deleteLike (@RequestParam("id") int id, Model model) {

        likeService.deleteLikeById(id);

        return "redirect:/likes";

    }

}
















