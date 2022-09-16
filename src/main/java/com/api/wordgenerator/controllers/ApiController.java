package com.api.wordgenerator.controllers;

import com.api.wordgenerator.dtos.WordDto;
import com.api.wordgenerator.models.WordModel;
import com.api.wordgenerator.services.WordService;
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

    @PostMapping
    public ResponseEntity<Object> saveWord(@RequestBody @Valid WordDto wordDto){
        if(!saveWordValidation(wordDto)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("This word \"%s\" already exists.",wordDto.getWord()));
        }
        var wordModel= new WordModel();
        BeanUtils.copyProperties(wordDto, wordModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordService.save(wordModel));
    }

    private boolean saveWordValidation(WordDto wordDto){
        if(wordService.existsByWord(wordDto.getWord())) {
            return false;
        }
        return true;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WordModel>> getAllWords(){
        return ResponseEntity.status(HttpStatus.OK).body(wordService.findAll());
    }

    @GetMapping("/show/{word}")
    public ResponseEntity<Object> getOneWord(@PathVariable(value = "word")String word){
        Optional<WordModel> optionalWordModel = wordService.findByWord(word);
        if(optionalWordModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word is not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(getWordRemoveUUID(optionalWordModel.get()));
    }

    @GetMapping("/show/random")
    public ResponseEntity<List<WordDto>> getRandomWord(){
        return ResponseEntity.status(HttpStatus.OK).body(getWordRemoveUUID(wordService.getRandomWord(1)));
    }

    @GetMapping("/show/random/{number}")
    public ResponseEntity<List<WordDto>> getRandomWord(@PathVariable(value = "number")Integer number){
        return ResponseEntity.status(HttpStatus.OK).body(getWordRemoveUUID(wordService.getRandomWord(number)));
    }

    private WordDto getWordRemoveUUID(WordModel wordModel){
        var wordDto= new WordDto();
        wordDto.setWord(wordModel.getWord());
        wordDto.setType(wordModel.getType());
        wordDto.setLanguage(wordModel.getLanguage());
        return wordDto;
    }

    private List<WordDto> getWordRemoveUUID(List<WordModel> wordModels){
        List<WordDto> wordDtos= new ArrayList<>();
        for(WordModel word: wordModels) {
            var wordDto = new WordDto();
            wordDto.setWord(word.getWord());
            wordDto.setType(word.getType());
            wordDto.setLanguage(word.getLanguage());
            wordDtos.add(wordDto);
        }
        return wordDtos;
    }

}
