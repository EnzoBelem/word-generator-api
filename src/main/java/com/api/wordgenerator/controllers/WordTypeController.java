package com.api.wordgenerator.controllers;

import com.api.wordgenerator.dtos.WordTypeDto;
import com.api.wordgenerator.models.WordTypeModel;
import com.api.wordgenerator.services.WordTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class WordTypeController {

    @Autowired
    WordTypeService wordTypeService;

    @PostMapping("/type/register")
    public ResponseEntity<Object> registerWordType(@RequestBody @Valid WordTypeDto wordTypeDto){
        if(wordTypeService.existsByType(wordTypeDto.getType())){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("this word type is already exists.");
        }
        WordTypeModel wordTypeModel= new WordTypeModel();
        BeanUtils.copyProperties(wordTypeDto, wordTypeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordTypeService.save(wordTypeModel));
    }

    @GetMapping("/type/get-all")
    public ResponseEntity<List<WordTypeModel>> getAllWordTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(wordTypeService.findAll());
    }

}
