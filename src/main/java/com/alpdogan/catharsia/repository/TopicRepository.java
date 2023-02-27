package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Topic findTopicById(int id);

    @Query("SELECT u FROM Topic u WHERE u.title = ?1")
    Topic findByTitle(String title);
}
