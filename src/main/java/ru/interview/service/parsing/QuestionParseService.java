package ru.interview.service.parsing;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class QuestionParseService {
    private String result;
    public QuestionParseService() {

    }

    public String getQuestion() {
        try {
            StringBuilder s = new StringBuilder();
            s.append("https://javastudy.ru/interview/basics-types-operators-arrays/");
            System.out.println(s);
            var document = Jsoup.connect( s.toString()).get();

            var titleElements = document.select("ol > li");
            result = titleElements.text();

            Elements paragraphs = document.getElementsByTag("li");
            for (Element paragraph : paragraphs) {
                if (paragraph.text().contains("?"))
                System.out.println(paragraph.text());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

}
