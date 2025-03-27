package com.gajanan.quiz_service.model.dto;

import lombok.Data;

@Data
public class QuizDTO {

    private String category;
    private Integer numOfQuestions;
    private String title;
}
