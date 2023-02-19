package ru.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Status;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT q FROM question_status q WHERE q.chatId = :chatId AND q.status = 'ACTIVE'")
    Optional<Status> findQuestionInActiveStatus(@Param("chatId") Long chatId);
    @Transactional
    @Modifying
    @Query("UPDATE question_status q SET q.status = 'CLOSED' WHERE q.chatId = :chatId")
    void closeStatus(@Param("chatId") Long chatId);


}
