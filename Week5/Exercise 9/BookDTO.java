package com.bookstoreapi.dto;

import org.springframework.hateoas.RepresentationModel;

public class BookDTO extends RepresentationModel<BookDTO> {
    private Long id;
    private String title;
    private String author;
    private double price;
    private String isbn;

    // Getters and Setters
}
