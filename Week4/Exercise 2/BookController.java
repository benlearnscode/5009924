package com.bookstoreapi.controller;

import com.bookstoreapi.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookList.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setId((long) (bookList.size() + 1));
        bookList.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setIsbn(book.getIsbn());
        }

        return existingBook;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookList.removeIf(book -> book.getId().equals(id));
        return "Book with ID " + id + " has been deleted.";
    }
}
