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

    @Autowired
    TopicService topicService;

    @GetMapping
    public String displayCategories (Model model) {

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("category", categories);

        return "list-categories";

    }

    // admin only?
    @GetMapping("/newCategory")
    public String displayCategoryForm (Model model) {

        Category category = new Category();
        model.addAttribute("category", category);

        return "new-category";

    }

    // again, admin only?
    @PostMapping("/addCategory")
    public String createCategory (@ModelAttribute("category") Category category) {

        categoryService.createCategory(category);

        return "redirect:/categories";

    }

    // what about this one?
    @GetMapping("/updateCategory")
    public String displayCategoryUpdateForm(@RequestParam("id") int id, Model model) {

        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);

        return "new-category";

    }

}

















