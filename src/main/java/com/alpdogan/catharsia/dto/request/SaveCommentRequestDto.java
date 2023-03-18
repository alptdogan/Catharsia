package com.alpdogan.catharsia.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveCommentRequestDto {

    private String text;

    private LocalDate createdAt;

    private int userId;

}
