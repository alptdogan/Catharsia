package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.dto.request.SaveCommentRequestDto;
import com.alpdogan.catharsia.dto.request.UpdateCommentRequestDto;
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
import java.util.Optional;


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

    public Comment findComment (Integer commentId) {

        return commentRepository.findById(commentId).get();

    }

    public String updateComment (UpdateCommentRequestDto updateCommentRequestDto) {

        int idCommentRequest = updateCommentRequestDto.getId();
        String textRequest = updateCommentRequestDto.getText();
        LocalDate createdAtRequest = updateCommentRequestDto.getCreatedAt();
        int userIdRequest = updateCommentRequestDto.getUserId();

        //Category category = categoryService.findCategory(categoryIdRequest);
        User user = userRepository.findById(userIdRequest).get();

        Optional<Comment> commentOptional = commentRepository.findById(idCommentRequest);
        Comment comment = commentOptional.get();

        comment.setText(textRequest);
        comment.setCreatedAt(createdAtRequest);
        comment.setUser(user);

        commentRepository.save(comment);

        return "Changes Saved Successfully.";

    }

    public String deleteCommentById(Integer commentId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.get();

        commentRepository.delete(comment);

        return "Comment Deleted.";
    }

}
