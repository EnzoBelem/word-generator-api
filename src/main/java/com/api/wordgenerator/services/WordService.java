package com.api.wordgenerator.services;

import com.api.wordgenerator.models.WordModel;
import com.api.wordgenerator.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    WordRepository wordRepository;

    public WordModel save(WordModel wordModel) {
        return wordRepository.save(wordModel);
    }

    public List<WordModel> findAll() {
        return wordRepository.findAll();
    }

    public boolean existsByWord(String word) {
        return wordRepository.existsByWordIgnoreCase(word);
    }

    public Optional<WordModel> findByWord(String word) {
        return wordRepository.findByWordIgnoreCase(word);
    }

    public List<WordModel> getRandomWord(int limit) {
        return wordRepository.getRandomWord(limit);
    }

    public List<WordModel> getRandomWordByType(int limit, String type) {
        return wordRepository.getRandomWordByType(limit, type);
    }

    public List<WordModel> getAllByType(String type) {
        return wordRepository.getAllByType(type);
    }

    public void delete(WordModel wordModel) {
         wordRepository.delete(wordModel);
    }
}
