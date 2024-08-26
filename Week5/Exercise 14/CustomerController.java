package com.bookstoreapi.controller;

import com.bookstoreapi.model.Customer;
import com.bookstoreapi.repository.CustomerRepository;
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
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer(null, "John Doe", "john.doe@example.com");

        mockMvc.perform(post("/customers")
                .contentType("application/json")
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer(null, "Jane Doe", "jane.doe@example.com");
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(get("/customers/{id}", savedCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer(null, "John Doe", "john.doe@example.com");
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(put("/customers/{id}", savedCustomer.getId())
                .contentType("application/json")
                .content("{\"name\":\"John Smith\",\"email\":\"john.smith@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Customer customer = new Customer(null, "Jane Doe", "jane.doe@example.com");
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(delete("/customers/{id}", savedCustomer.getId()))
                .andExpect(status().isNoContent());
    }
}
