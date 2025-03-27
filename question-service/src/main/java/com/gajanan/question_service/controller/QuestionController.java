package com.gajanan.question_service.controller;



import com.gajanan.question_service.model.Question;
import com.gajanan.question_service.model.QusetionWrapper;
import com.gajanan.question_service.model.Response;
import com.gajanan.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping()
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
       return questionService.addQuestion(question);
    }

    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable Integer id,  @RequestBody Question question) {
        return questionService.updateQuestion(id,question);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numOfQuestion){
        return questionService.getQuestionsForQuiz(category,numOfQuestion);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QusetionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
