package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.entity.Comment;
import com.alpdogan.catharsia.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        return commentRepository.findCommentById(id);
    }

    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateCommentById(int id, Comment comment) {
        comment.setId(id);
        commentRepository.save(comment);
    }

    public void deleteCommentById(int id) {
        commentRepository.delete(commentRepository.findCommentById(id));
    }


}
