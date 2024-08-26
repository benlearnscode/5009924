package com.bookstoreapi.controller;

import com.bookstoreapi.entity.Book;
import com.bookstoreapi.exception.BookNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    // Example method to demonstrate exception handling
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // Simulate fetching a book by ID
        if (id == null || id <= 0) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }

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
