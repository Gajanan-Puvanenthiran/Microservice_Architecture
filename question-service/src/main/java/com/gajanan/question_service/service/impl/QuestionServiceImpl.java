package com.gajanan.question_service.service.impl;



import com.gajanan.question_service.model.Question;
import com.gajanan.question_service.model.QusetionWrapper;
import com.gajanan.question_service.model.Response;
import com.gajanan.question_service.repository.QuestionRepo;
import com.gajanan.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
       try{
           return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public String updateQuestion(Integer id, Question question) {
        Optional<Question> optional = questionRepo.findById(id);
        if (optional.isPresent()) {
            questionRepo.save(question);
            return "Success";
        }
        else{
            return "Fail";
        }

    }

    @Override
    public String deleteQuestion(Integer id) {
        Optional<Question> optional = questionRepo.findById(id);
        if (optional.isPresent()) {
            questionRepo.delete(optional.get());
            return "Success";
        }
        else{
            return "Fail";
        }
    }

    @Override
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQuestion) {
        List<Integer> questions=questionRepo.findRandomQuestionsByCategory(category, numOfQuestion);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QusetionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QusetionWrapper> qusetionWrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for(Integer id:questionIds){
            questions.add(questionRepo.findById(id).get());
        }

        for (Question question:questions) {
            QusetionWrapper qusetionWrapper=new QusetionWrapper();
            qusetionWrapper.setId(question.getId());
            qusetionWrapper.setQuestionTitle(question.getQuestionTitle());
            qusetionWrapper.setOption1(question.getOption1());
            qusetionWrapper.setOption2(question.getOption2());
            qusetionWrapper.setOption3(question.getOption3());
            qusetionWrapper.setOption4(question.getOption4());

            qusetionWrappers.add(qusetionWrapper);
        }
        return new ResponseEntity<>(qusetionWrappers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score=0;
        for(Response response:responses){
            Question question=questionRepo.findById(response.getId()).get();
            if(response.getAnswer().equals(question.getRightAnswer()))
                score++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }


}
