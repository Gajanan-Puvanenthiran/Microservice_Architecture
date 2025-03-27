package com.gajanan.quiz_service.service;

;
import com.gajanan.quiz_service.feign.QuizInterface;
import com.gajanan.quiz_service.model.Quiz;
import com.gajanan.quiz_service.model.QusetionWrapper;
import com.gajanan.quiz_service.model.Response;
import com.gajanan.quiz_service.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numOfQuestion, String title) {

        List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category,numOfQuestion).getBody();

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QusetionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isPresent()) {
            List<Integer> questionIds = quiz.get().getQuestionIds();
            ResponseEntity<List<QusetionWrapper>> questions = quizInterface.getQuestionFromId(questionIds);
            return questions;
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer>score=quizInterface.getScore(responses);

        return score;
    }
}
