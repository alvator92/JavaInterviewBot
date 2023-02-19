package ru.interview.service;

import ru.interview.model.Question;

import java.util.List;
import java.util.Random;

public class RandomQuestionService {

    /**
     * Выбрать случйный вопрос из списка
     */
    public static Question getQuestionFromList(List<Question> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
