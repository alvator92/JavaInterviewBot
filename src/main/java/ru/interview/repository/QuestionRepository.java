package ru.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.interview.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
