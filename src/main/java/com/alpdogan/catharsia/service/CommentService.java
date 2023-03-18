package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.dto.request.SaveCommentRequestDto;
import com.alpdogan.catharsia.dto.response.CommentResponseDto;
import com.alpdogan.catharsia.entity.Comment;
import com.alpdogan.catharsia.entity.User;
import com.alpdogan.catharsia.repository.CommentRepository;
import com.alpdogan.catharsia.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public String saveComment (SaveCommentRequestDto saveCommentRequestDto) {

        String textRequest = saveCommentRequestDto.getText();
        LocalDate createdAtRequest = saveCommentRequestDto.getCreatedAt();
        int userIdRequest = saveCommentRequestDto.getUserId();
        //int categoryIdRequest = saveCommentRequestDto.getCategoryId();

        //Category category = categoryService.findCategory(categoryIdRequest);
        User user = userRepository.findById(userIdRequest).get();

        Comment comment = new Comment();

        comment.setText(textRequest);
        comment.setCreatedAt(createdAtRequest);
        comment.setUser(user);
        //comment.setCategory(category);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        //category.setComments(commentList);

        user.setComments(commentList);

        commentRepository.save(comment);

        return comment.getText() + " Has Been Successfully Created.";

    }

    public List<CommentResponseDto> findAllComments() {

        Iterable<Comment> comments = commentRepository.findAll();

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for (Comment comment : comments) {

            CommentResponseDto commentResponseDto = modelMapper.map(comment, CommentResponseDto.class);

            commentResponseDtos.add(commentResponseDto);

        }

        return commentResponseDtos;

    }



}
