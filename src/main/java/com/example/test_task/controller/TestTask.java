package com.example.test_task.controller;

import com.example.test_task.entity.Words;
import com.example.test_task.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class TestTask {

    private final WordsService wordsService;

    @Autowired
    public TestTask(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @PostMapping(value = "/game")
    public Words game(@RequestBody Words jWords) {
        return wordsService.checkWords(jWords);
    }
}
