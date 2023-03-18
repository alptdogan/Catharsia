package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.dto.request.SaveCommentRequestDto;
import com.alpdogan.catharsia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/saveComment")
    public ResponseEntity<String> saveComment(@RequestBody SaveCommentRequestDto saveCommentRequestDto) {

        String commentSaveDescription = commentService.saveComment(saveCommentRequestDto);

        return new ResponseEntity<>(commentSaveDescription, HttpStatus.OK);

    }

}
