package ru.parser.simbirSoft.services;

import ru.parser.simbirSoft.models.Word;
import ru.parser.simbirSoft.repositories.WordRepository;

import java.util.Map;

public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public void saveMap(Map<String, Integer> words) {
       for (String key : words.keySet()) {
           Word word = new Word(key, words.get(key));
           wordRepository.save(word);
       }
    }
}
