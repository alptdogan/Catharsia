package com.alpdogan.catharsia.controller;

import com.alpdogan.catharsia.dto.request.SaveCommentRequestDto;
import com.alpdogan.catharsia.dto.request.UpdateCommentRequestDto;
import com.alpdogan.catharsia.dto.response.CommentResponseDto;
import com.alpdogan.catharsia.entity.Comment;
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

    @GetMapping("/findCommentById")
    public ResponseEntity<Comment> findCommentById (@RequestParam Integer commentId) {

        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(comment, HttpStatus.OK);

    }

    @PostMapping("/updateComment")
    public ResponseEntity<String> updateComment (@RequestBody UpdateCommentRequestDto updateCommentRequestDto) {

        String updateCommentDescription = commentService.updateComment(updateCommentRequestDto);

        return new ResponseEntity<>(updateCommentDescription, HttpStatus.OK);

    }

    @DeleteMapping("/deleteComment")
    public ResponseEntity<String> deleteCommentById(@RequestParam Integer commentId) {

        String deleteCommentDescription = commentService.deleteCommentById(commentId);

        return new ResponseEntity<>(deleteCommentDescription, HttpStatus.OK);

    }

}
