package com.alpdogan.catharsia.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCommentRequestDto {

    private int id;

    private String text;

    private LocalDate createdAt;

    private int userId;

}
