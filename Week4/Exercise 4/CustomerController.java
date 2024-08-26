package com.bookstoreapi.controller;

import com.bookstoreapi.entity.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // POST endpoint to create a new customer with JSON request body
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        // Process and save the customer (simulated here)
        return customer;
    }

    // Endpoint to process form data for customer registrations
    @PostMapping("/register")
    public Customer registerCustomer(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String address) {
        // Process and save the customer (simulated here)
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        return customer;
    }
}
