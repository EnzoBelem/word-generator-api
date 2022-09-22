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
    @NotBlank
    @Size(max = 20)
    private String gender;
    @NotBlank
    @Size(max = 20)
    private String number;
    @Size(max = 250)
    private String meaning;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
