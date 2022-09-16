package com.api.wordgenerator.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WordDto {

    @NotBlank
    @Size(max = 50)
    private String word;
    @NotBlank
    @Size(max = 30)
    private String type;
    @NotBlank
    @Size(max = 30)
    private String language;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
