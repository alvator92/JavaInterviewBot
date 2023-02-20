package ru.interview.model;

import lombok.Getter;

import javax.persistence.*;

@Entity(name = "section")
@Getter
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"chatId\"")
    private Long chatId;

    @Column(name = "section")
    private String section;

    @Column(name = "status")
    private String status;

    public static class Builder {
        Section section;

        public Builder() {
            section = new Section();
        }

        public Builder withChatId(Long val) {
            section.chatId = val;
            return this;
        }

        public Builder withSection(String val) {
            section.section = val;
            return this;
        }

        public Builder withStatus(String val) {
            section.status = val;
            return this;
        }

        public Section build() {
            return section;
        }
    }
}
