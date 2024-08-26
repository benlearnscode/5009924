package com.bookstoreapi.controller;

import com.bookstoreapi.model.Book;
import com.bookstoreapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book(null, "New Book", "Author", 20.00, "1234567890123");

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content("{\"title\":\"New Book\",\"author\":\"Author\",\"price\":20.00,\"isbn\":\"1234567890123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book(null, "Existing Book", "Author", 25.00, "1234567890124");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(get("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Existing Book"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book(null, "Book to Update", "Author", 30.00, "1234567890125");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(put("/books/{id}", savedBook.getId())
                .contentType("application/json")
                .content("{\"title\":\"Updated Title\",\"author\":\"Updated Author\",\"price\":35.00,\"isbn\":\"1234567890125\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteBook() throws Exception {
        Book book = new Book(null, "Book to Delete", "Author", 40.00, "1234567890126");
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(delete("/books/{id}", savedBook.getId()))
                .andExpect(status().isNoContent());
    }
}
