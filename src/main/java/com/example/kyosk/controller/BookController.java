package com.example.kyosk.controller;

import com.example.kyosk.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @GetMapping("/books")
    public List getBooks() {
        return List.of(
                new Book("1", "The Hobbit", "J.R.R. Tolkien"),
                new Book("2", "1984", "George Orwell"),
                new Book("3", "Pride and Prejudice", "Jane Austen")
        );
    }

    @GetMapping("/health")
    public String health() {
        return "Up and running!";
    }
}
