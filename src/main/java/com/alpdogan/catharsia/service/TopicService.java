package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.entity.Comment;
import com.alpdogan.catharsia.entity.Topic;
import com.alpdogan.catharsia.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    CommentService commentService;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(int id) {
        return topicRepository.findTopicById(id);
    }

    public void createTopic(Topic topic) {
        //commentService.createComment(comment);
        topicRepository.save(topic);
    }

    public Topic getTopicByTitle(String title) {
        return topicRepository.findByTitle(title);
    }

    public void updateTopic(Topic topic) {
        //topic.setTitle(topic.getTitle());
        // we can set whatever field we need to update, later.
        topicRepository.save(topic);
    }

    public void deleteTopicById(int id) {
        topicRepository.delete(topicRepository.findTopicById(id));
    }

}
