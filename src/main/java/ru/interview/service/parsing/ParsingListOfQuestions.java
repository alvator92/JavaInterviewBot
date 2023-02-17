package ru.interview.service.parsing;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.interview.model.Question;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class ParsingListOfQuestions {
    private String result;
    private List<Question> listOfQuestions;
    private Question question;


    public List<Question> getQuestion(String name, String section) {
        listOfQuestions = new ArrayList<>();

        try {
            StringBuilder s = new StringBuilder();
//            s.append("https://javastudy.ru/interview/basics-types-operators-arrays/");
            s.append(section);
            var document = Jsoup.connect( s.toString()).get();

            var titleElements = document.select("ol > li");
            result = titleElements.text();

            Elements paragraphs = document.getElementsByTag("h4");
            for (Element paragraph : paragraphs) {
                question = new Question();
                question.setQuest_name(paragraph.text().replaceAll("\\d+\\.\\s?", ""));
                question.setSection_name(name);
                listOfQuestions.add(question);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfQuestions;
    }
}
