package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Topic;
import com.alpdogan.catharsia.service.CommentService;
import com.alpdogan.catharsia.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public String displayTopics (Model model) {

        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topic", topics);

        return "list-topics";

    }

    @GetMapping("/details")
    public String displayTopicDetails(@RequestParam("id") int id, Model model) {

        Topic topic = topicService.getTopicById(id);
        model.addAttribute("topic", topic);

        return "topic-details";

    }

    @GetMapping("/newTopic")
    public String displayTopicForm (Model model) {

        Topic topic = new Topic();
        model.addAttribute("topic", topic);

        return "new-topic";

    }

    @PostMapping("/addTopic")
    public String createTopic (@ModelAttribute("topic") Topic topic) {

        topicService.createTopic(topic);

        return "redirect:/topics";

    }

    @GetMapping("/updateTopic")
    public String displayTopicUpdateForm(@RequestParam("id") int id, Model model) {

        Topic topic = topicService.getTopicById(id);
        model.addAttribute("topic", topic);

        return "new-topic";

    }

    @GetMapping("/deleteTopic")
    public String deleteTopic(@RequestParam("id") int id, Model model) {

        topicService.deleteTopicById(id);

        return "redirect:/topics";

    }

}


















