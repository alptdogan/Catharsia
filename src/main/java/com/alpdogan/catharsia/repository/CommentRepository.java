package com.alpdogan.catharsia.repository;

import com.alpdogan.catharsia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(int id);

}
