package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.dto.request.SaveCommentRequestDto;
import com.alpdogan.catharsia.dto.response.CommentResponseDto;
import com.alpdogan.catharsia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findAllComments")
    public ResponseEntity<List<CommentResponseDto>> findAllComments() {

        List<CommentResponseDto> commentResponseDtos = commentService.findAllComments();

        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);

    }

}
