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
    @Column(nullable = false, length = 30)
    private String type;
    @Column(nullable = false, length = 30)
    private String language;

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
