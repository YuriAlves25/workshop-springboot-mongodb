package com.example.workshopmongo.dto;

import com.example.workshopmongo.domain.User;

import java.io.Serializable;

public class AuthorDto implements Serializable {
    private String id;
    private String name;

    public AuthorDto() {
    }

    public AuthorDto(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
