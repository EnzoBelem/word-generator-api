package com.api.wordgenerator.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WordTypeDto {

    @NotBlank
    @Size(max = 30)
    private String type;

    @NotBlank
    @Size(max = 150)
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

}
