package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.entity.Topic;
import com.alpdogan.catharsia.entity.User;
import com.alpdogan.catharsia.repository.TopicRepository;
import com.alpdogan.catharsia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(int id) {
        return topicRepository.findTopicById(id);
    }

    public void createTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopicById(int id, Topic topic) {
        topic.setId(id);
        topicRepository.save(topic);
    }

    public void deleteTopicById(int id) {
        topicRepository.delete(topicRepository.findTopicById(id));
    }

}
