package ru.interview.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.interview.service.QuestionController;


@RestController
@Slf4j
public class QuestionRestController {

    @Autowired
    private QuestionController controller;

    @RequestMapping(value = "/save")
    ResponseEntity<String> Response(@RequestParam(value = "chapter") String chapter,
                                    @RequestParam(value = "question") String question) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type","application/json; charset=utf-8");
        log.info("chapter : " + chapter + "\n" + "question : " + question);
        return new ResponseEntity<>("response",httpHeaders, HttpStatus.OK);
    }
}
