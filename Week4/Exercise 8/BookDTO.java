package com.bookstoreapi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    private String author;

    @Min(0)
    private double price;

    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;

    // Getters and Setters
}
