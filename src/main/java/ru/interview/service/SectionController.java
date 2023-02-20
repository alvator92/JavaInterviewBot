package ru.interview.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.configuration.JpaBean;
import ru.interview.model.Section;
import ru.interview.model.Status;

@Component
public class SectionController {
    @Autowired
    private JpaBean getBean;

    /**
     * Найти вопрос в активном статусе
     */
    public Section findSectionInActiveStatus(Long chatId) {
        return getBean.sectionService().getSection(chatId);
    }

    /**
     * Создание записи о том что вопрос был задан и находится в активном статусе
     */
    public void registerSection(long chatId, String section) {

        Section sec = new Section.Builder()
                .withChatId(chatId)
                .withSection(section)
                .withStatus("ACTIVE")
                .build();

        getBean.sectionService().save(sec);

    }

    /**
     * Закрыть статус
     */
    public void closeActiveStatus(long chatId) {
        getBean.sectionService().closeStatus(chatId);
    }

}
