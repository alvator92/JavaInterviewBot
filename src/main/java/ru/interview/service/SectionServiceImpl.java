package ru.interview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.model.Section;
import ru.interview.model.Status;
import ru.interview.repository.SectionRepository;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRepository repository;

    @Override
    public void save(Section section) {
        repository.save(section);
    }

    @Override
    public Section getSection(Long chatId) {
        try {
            Section section = repository.findQuestionInActiveStatus(chatId).get();
            return section;
        } catch (NoSuchElementException e) {
            log.error("Нет выбран раздел : " + e.toString());
        }
        return null;
    }

    @Override
    public void closeStatus(Long chatId) {
        repository.closeStatus(chatId);
    }
}
