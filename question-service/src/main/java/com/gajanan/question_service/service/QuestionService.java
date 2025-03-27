package com.gajanan.question_service.service;



import com.gajanan.question_service.model.Question;
import com.gajanan.question_service.model.QusetionWrapper;
import com.gajanan.question_service.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    ResponseEntity<List<Question>>  getAllQuestions();

    ResponseEntity<List<Question>> getQuestionByCategory(String category);

    ResponseEntity<String> addQuestion(Question question);

    String updateQuestion(Integer id, Question question);

    String deleteQuestion(Integer id);

    ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQuestion);

    ResponseEntity<List<QusetionWrapper>> getQuestionFromId(List<Integer> questionIds);

    ResponseEntity<Integer> getScore(List<Response> responses);
}
