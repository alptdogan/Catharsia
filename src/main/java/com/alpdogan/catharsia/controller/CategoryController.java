package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.entity.Category;
import com.alpdogan.catharsia.service.CategoryService;
import com.alpdogan.catharsia.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String displayCategories (Model model) {

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("category", categories);

        return "list-categories";

    }

}

















