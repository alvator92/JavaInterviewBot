package ru.interview.model;

import lombok.Getter;

import javax.persistence.*;

@Entity(name = "question_status")
@Getter
@Table(name = "question_status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"chatId\"")
    private Long chatId;

    @Column(name = "question")
    private String question;

    @Column(name = "status")
    private String status;

    public static class Builder {
        Status status;

        public Builder() {
            status = new Status();
        }

        public Builder withChatId(Long val) {
            status.chatId = val;
            return this;
        }

        public Builder withQuestion(String val) {
            status.question = val;
            return this;
        }

        public Builder withStatus(String val) {
            status.status = val;
            return this;
        }

        public Status build() {
            return status;
        }
    }

}
