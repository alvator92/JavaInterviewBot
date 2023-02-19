package ru.interview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Question;
import ru.interview.repository.QuestionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Slf4j
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository repository;

    @Override
    public void save(Question question) {
        repository.save(question);
    }

    @Transactional(readOnly = true)
    public Optional<Question> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Question> saveAll(List<Question> list) {
        return repository.saveAll(list);
    }

    @Override
    public Question findAnswerByQuestion(String question) throws NoSuchElementException {
        try {
            Question quest = repository.findAnswerByQuestion(question).get();
            return quest;
        } catch (NoSuchElementException e) {
            log.error("Искомый вопрос не был найден : " + e.toString());
        }
        return null;
    }

    @Override
    public List<Question> findQuestionBySection(String section) {
        return repository.findAllQuestionsBySection(section);
    }
}
