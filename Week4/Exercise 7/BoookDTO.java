package com.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private double price;
    private String isbn;

    // Getters and Setters
}
