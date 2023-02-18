package ru.interview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "questions")
@Getter
@Setter
@Table(name = "questions", schema = "public")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quest_name")
    private String quest_name;

    @Column(name = "section_name")
    private String section_name;

    @Column(name = "answer")
    private String answer;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quest_name='" + quest_name + '\'' +
                ", section_name='" + section_name + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
