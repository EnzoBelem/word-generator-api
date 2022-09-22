package com.api.wordgenerator.controllers;


import com.api.wordgenerator.dtos.WordDto;
import com.api.wordgenerator.models.WordModel;
import com.api.wordgenerator.models.WordTypeModel;
import com.api.wordgenerator.services.WordService;
import com.api.wordgenerator.services.WordTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/word")
public class ApiController {

    @Autowired
    WordService wordService;
    @Autowired
    WordTypeService wordTypeService;

    @PostMapping
    public ResponseEntity<Object> saveWord(@RequestBody @Valid WordDto wordDto){
        var validation = saveWordValidationWord(wordDto);
        if(validation.getStatusCode() == HttpStatus.CONFLICT){
            return validation;
        }
        var wordModel= new WordModel();
        BeanUtils.copyProperties(wordDto, wordModel);
        wordModel.setWordTypeModel(wordTypeService.findByType(wordDto.getType()).get());
        wordModel.wordModelCapitalize();
        return ResponseEntity.status(HttpStatus.CREATED).body(wordService.save(wordModel));
    }

    private ResponseEntity<Object> saveWordValidationWord(WordDto wordDto){
        if(wordService.existsByWord(wordDto.getWord())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("This word \"%s\" already exists.",wordDto.getWord()));
        }
        if(wordTypeService.findByType(wordDto.getType()).isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The word type provided is invalid.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("the word was validated.");
    }

    @GetMapping("/types")
    public  ResponseEntity<List<String>> getWordTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(wordTypeService.getAllTypes());
    }

    @GetMapping("/find/{word}")
    public ResponseEntity<Object> getOneWord(@PathVariable(value = "word")String word){
        Optional<WordModel> optionalWordModel = wordService.findByWord(word);
        if(optionalWordModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word is not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(optionalWordModel.get()));
    }

    @GetMapping("/show/all")
    public ResponseEntity<List<WordDto>> getAllWords(){
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(wordService.findAll()));
    }

    @GetMapping("/show/all/{type}")
    public ResponseEntity<Object> getAllWords(@PathVariable(value = "type")String type){
        Optional<WordTypeModel> optionalWordTypeModel = wordTypeService.findByType(type);
        if(!optionalWordTypeModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The word type provide is invalid.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(wordService.getAllByType(type)));
    }

    @GetMapping("/show/random")
    public ResponseEntity<List<WordDto>> getRandomWord(){
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(wordService.getRandomWord(1)));
    }

    @GetMapping("/show/random/{number}")
    public ResponseEntity<List<WordDto>> getRandomWord(@PathVariable(value = "number")Integer number){
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(wordService.getRandomWord(number)));
    }

    @GetMapping("/show/random-by-type/{type}")
    public ResponseEntity<Object> getRandomWordByType(@PathVariable(value = "type")String type){
        Optional<WordTypeModel> optionalWordTypeModel = wordTypeService.findByType(type);
        if(!optionalWordTypeModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The word type provide is invalid.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(wordService.getRandomWordByType(1, type)));
    }

    @GetMapping("/show/random-by-type/{type}/{number}")
    public ResponseEntity<Object> getRandomWordByType(@PathVariable(value = "type")String type, @PathVariable(value = "number")Integer number){
        Optional<WordTypeModel> optionalWordTypeModel = wordTypeService.findByType(type);
        if(!optionalWordTypeModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The word type provide is invalid.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(getMethodWordFormat(wordService.getRandomWordByType(number, type)));
    }

    private WordDto getMethodWordFormat(WordModel wordModel){
        var wordDto= new WordDto();
        wordDto.setWord(wordModel.getWord());
        wordDto.setType(wordModel.getWordTypeModel().getType());
        wordDto.setLanguage(wordModel.getLanguage());
        wordDto.setGender(wordModel.getGender());
        wordDto.setNumber(wordModel.getNumber());
        wordDto.setMeaning(wordModel.getMeaning());
        return wordDto;
    }

    private List<WordDto> getMethodWordFormat(List<WordModel> wordModels){
        List<WordDto> wordDtos= new ArrayList<>();
        for(WordModel word: wordModels) {
            var wordDto = new WordDto();
            wordDto.setWord(word.getWord());
            wordDto.setType(word.getWordTypeModel().getType());
            wordDto.setLanguage(word.getLanguage());
            wordDto.setGender(word.getGender());
            wordDto.setNumber(word.getNumber());
            wordDto.setMeaning(word.getMeaning());
            wordDtos.add(wordDto);
        }
        return wordDtos;
    }

}
