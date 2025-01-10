package com.example.kyosk.repository;

import com.example.kyosk.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAuthor(String author);
    Book findByTitle(String title);
}
