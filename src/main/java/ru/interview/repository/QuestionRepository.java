package ru.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.interview.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM questions q WHERE q.question = :question")
    Optional<Question> findAnswerByQuestion(@Param("question") String question);

    @Query("SELECT q FROM questions q WHERE q.section_name = :section")
    List<Question> findAllQuestionsBySection(@Param("section") String section);

}
