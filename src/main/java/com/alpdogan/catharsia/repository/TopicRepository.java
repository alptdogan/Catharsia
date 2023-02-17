package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Topic findTopicById(int id);

}
