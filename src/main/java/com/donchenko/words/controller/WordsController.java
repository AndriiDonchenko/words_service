package com.donchenko.words.controller;

import com.donchenko.words.WordsArray;
import com.donchenko.words.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WordsController {

    @Autowired
    private WordsService wordsService;

    @PostMapping("/words")
    public WordsArray getValidWords(@RequestBody WordsArray words) {
        return wordsService.validateArray(words);
    }

}
