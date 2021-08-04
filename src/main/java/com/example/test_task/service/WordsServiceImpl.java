package com.example.test_task.service;

import com.example.test_task.entity.Words;
import org.springframework.stereotype.Service;

@Service
public class WordsServiceImpl implements WordsService {

    @Override
    public Words checkWords(Words jWords) {
        int countWords = 0;
        String emptyWord = "";

        for (String word: jWords.getWords()) {
            word = word.trim();
            if (!word.isEmpty()) {
                if (countWords == 0) {
                    emptyWord = word.substring(word.length() - 1);
                    countWords++;
                    continue;
                }
                if (!word.substring(0,1).equals(emptyWord)) {
                    emptyWord = word.substring(word.length() - 1);
                    countWords++;
                } else break;
            } else break;
        }

        String[] outputWords = new String[countWords];
        if (countWords >= 0) System.arraycopy(jWords.getWords(), 0, outputWords, 0, countWords);
        jWords.setWords(outputWords);
        return jWords;
    }
}
