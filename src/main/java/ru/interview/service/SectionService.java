package ru.interview.service;

import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Section;
import ru.interview.model.Status;

public interface SectionService {

    @Transactional
    void save(Section section);

    Section getSection(Long chatId);

    void closeStatus(Long chatId);

}
