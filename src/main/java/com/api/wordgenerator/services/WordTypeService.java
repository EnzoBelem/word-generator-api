package com.api.wordgenerator.services;

import com.api.wordgenerator.models.WordModel;
import com.api.wordgenerator.models.WordTypeModel;
import com.api.wordgenerator.repositories.WordTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordTypeService {

    @Autowired
    WordTypeRepository wordTypeRepository;

    public WordTypeModel save(WordTypeModel wordTypeModel) {
        return  wordTypeRepository.save(wordTypeModel);
    }

    public boolean existsByType(String type) {
        return wordTypeRepository.existsByType(type);
    }


    public List<WordTypeModel> findAll() {
        return wordTypeRepository.findAll();
    }

    public Optional<WordTypeModel> findByType(String type) {
        return wordTypeRepository.findByTypeIgnoreCase(type);
    }
}
