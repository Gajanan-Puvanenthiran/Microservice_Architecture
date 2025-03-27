package com.gajanan.quiz_service.controller;


import com.gajanan.quiz_service.model.QusetionWrapper;
import com.gajanan.quiz_service.model.Response;
import com.gajanan.quiz_service.model.dto.QuizDTO;
import com.gajanan.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String>createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumOfQuestions(),quizDTO.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QusetionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
