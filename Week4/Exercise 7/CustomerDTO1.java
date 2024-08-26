package com.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
    private Long id;
    private String name;
    private String email;

    @JsonCreator
    public CustomerDTO(@JsonProperty("id") Long id, 
                       @JsonProperty("name") String name, 
                       @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
}
