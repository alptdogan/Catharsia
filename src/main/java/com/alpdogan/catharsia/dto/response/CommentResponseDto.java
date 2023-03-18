package com.alpdogan.catharsia.dto.response;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class CommentResponseDto {

    private String text;

    private LocalDate createdAt;

    private int userId;

    //private int categoryId;

}
