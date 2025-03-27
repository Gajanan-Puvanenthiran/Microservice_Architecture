package com.gajanan.quiz_service.repository;



import com.gajanan.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
