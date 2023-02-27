package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Topic;
import com.alpdogan.catharsia.repository.TopicRepository;
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
    TopicRepository topicRepository;

    @Autowired
    CommentService commentService;

    /*
    @Autowired
    CategoryService categoryService;
     */

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

        //List<Comment> comments = commentService.getAllComments();
        //List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("topic", topic);
        //model.addAttribute("allComments", comments);
        //model.addAttribute("allCategories", categories);

        return "new-topic";

    }

    @PostMapping("/addTopic")
    public String createTopic (@ModelAttribute("topic") Topic topic //,
                                    //@RequestParam List<Long> comments,
                                    //@RequestParam Category category
                                            ) {

        topicService.createTopic(topic);

        return "redirect:/topics";

    }

    @GetMapping("/update")
    public String displayTopicUpdateForm(@RequestParam("id") int id, Model model) {

        Topic topic = topicService.getTopicById(id);

        model.addAttribute("topic", topic);

        return "update-topic";

    }

    @PostMapping("/updateTopic")
    public String updateTopic(@ModelAttribute ("topic") Topic topic,
                              @RequestParam(value = "title") String title //,
                              //@RequestParam List<Long> categories
                                            ) {

        Topic topic1 = topicService.getTopicByTitle(title);

        topic1.setTitle(topic.getTitle());

        topicService.updateTopic(topic1);

        return "redirect:/topics";

    }

    @GetMapping("/deleteTopic")
    public String deleteTopic(@RequestParam("id") int id, Model model) {

        topicService.deleteTopicById(id);

        return "redirect:/topics";

    }

}


















