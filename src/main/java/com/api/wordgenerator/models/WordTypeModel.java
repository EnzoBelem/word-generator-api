package com.api.wordgenerator.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_WORD_TYPE")
public class WordTypeModel {

    @Id
    @Column(updatable = false, unique = true, nullable = false, length = 30, name = "word_type")
    private String type;
    @Column(nullable = false, length = 150, name = "type_description")
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WordTypeModel{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
