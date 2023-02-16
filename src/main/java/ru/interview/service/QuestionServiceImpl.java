package ru.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Question;
import ru.interview.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Component
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
}
