package com.api.wordgenerator.models;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_WORD")
public class WordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true, length = 50)
    private String word;
    @ManyToOne
    @JoinColumn(nullable = false, name= "word_type")
    private WordTypeModel wordTypeModel;
    @Column(nullable = false, length = 30, name = "word_gender")
    private String gender;
    @Column(nullable = false, length = 20, name = "word_number")
    private String number;
    @Column(nullable = false, length = 20, name = "word_lang")
    private String language;
    @Column(length = 400, name = "word_meaning")
    private String meaning;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordTypeModel getWordTypeModel() {
        return wordTypeModel;
    }

    public void setWordTypeModel(WordTypeModel wordTypeModel) {
        this.wordTypeModel = wordTypeModel;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "WordModel{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", wordTypeModel=" + wordTypeModel +
                ", gender='" + gender + '\'' +
                ", number='" + number + '\'' +
                ", language='" + language + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }

    public void wordModelCapitalize(){
        this.word= StringUtils.capitalize(StringUtils.lowerCase(this.word));
        this.gender= StringUtils.capitalize(StringUtils.lowerCase(this.gender));
        this.number= StringUtils.capitalize(StringUtils.lowerCase(this.number));
        this.language= StringUtils.upperCase(StringUtils.lowerCase(this.language));
        this.meaning= StringUtils.capitalize(StringUtils.lowerCase(this.meaning));
    }

}
