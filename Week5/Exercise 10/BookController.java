package com.bookstoreapi.controller;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> getBook(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        BookDTO book = bookService.getBook(id);
        if (book != null) {
            Link selfLink = WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withSelfRel();
            EntityModel<BookDTO> resource = EntityModel.of(book, selfLink);
            return ResponseEntity.ok(resource);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<BookDTO>>> getAllBooks(@RequestHeader HttpHeaders headers) {
        List<BookDTO> books = bookService.getAllBooks();
        List<EntityModel<BookDTO>> resources = books.stream()
            .map(book -> {
                Link selfLink = WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withSelfRel();
                return EntityModel.of(book, selfLink);
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        if (updatedBook != null) {
            Link selfLink = WebMvcLinkBuilder.linkTo(BookController.class).slash(updatedBook.getId()).withSelfRel();
            EntityModel<BookDTO> resource = EntityModel.of(updatedBook, selfLink);
            return ResponseEntity.ok(resource);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
