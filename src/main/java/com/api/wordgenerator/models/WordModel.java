package com.api.wordgenerator.models;

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
    @JoinColumn(name= "word_type")
    private WordTypeModel wordTypeModel;

    @Column(nullable = false, length = 30)
    private String language;

    @Column(length = 250)
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public WordTypeModel getWordTypeModel() {
        return wordTypeModel;
    }

    public void setWordTypeModel(WordTypeModel wordTypeModel) {
        this.wordTypeModel = wordTypeModel;
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
                ", wordTypeModel=" + wordTypeModel.toString() +
                ", language='" + language + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}
