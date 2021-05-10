package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Person {
    @NotBlank
    private final String name;
    private final int id;

    public int getId() {
        return id;
    }

    public Person(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
