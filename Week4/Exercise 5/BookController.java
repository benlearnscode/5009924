package com.bookstoreapi.controller;

import com.bookstoreapi.entity.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    // Customizing HTTP status code for a POST request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        // Process and save the book (simulated here)
        return book;
    }

    // Adding custom headers to the response using ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle("Sample Book");
        book.setAuthor("Sample Author");
        book.setPrice(29.99);
        book.setIsbn("123-4567890123");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "This is a custom header value");

        return new ResponseEntity<>(book, headers, HttpStatus.OK);
    }
}
