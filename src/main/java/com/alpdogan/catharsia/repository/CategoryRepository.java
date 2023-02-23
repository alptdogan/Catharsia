package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(int id);

}
