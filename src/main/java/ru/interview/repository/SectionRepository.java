package ru.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Section;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("SELECT s FROM section s WHERE s.chatId = :chatId AND s.status = 'ACTIVE'")
    Optional<Section> findQuestionInActiveStatus(@Param("chatId") Long chatId);

    @Transactional
    @Modifying
    @Query("UPDATE section s SET s.status = 'CLOSED' WHERE s.chatId = :chatId")
    void closeStatus(@Param("chatId") Long chatId);
}
