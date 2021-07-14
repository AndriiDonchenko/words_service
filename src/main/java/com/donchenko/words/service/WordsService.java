package com.donchenko.words.service;

import com.donchenko.words.WordsArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordsService {
    public WordsArray validateArray(WordsArray wordsArray) {
        String[] words = wordsArray.getWords();
        List<String> validWords = new ArrayList<>();

        for(int i = 0; i < words.length - 1; i++) {
            if(words[i].equals("")) {
                break;
            }

            if(i == 0) {
                validWords.add(words[i]);
            }

            if(words[i+1].startsWith(words[i].substring(words[i].length() - 1))) {
                validWords.add(words[i+1]);
            } else {
                break;
            }

        }

        WordsArray validArray = new WordsArray();
        validArray.setWords(validWords.toArray(new String[0]));

        return validArray;
    }
}
