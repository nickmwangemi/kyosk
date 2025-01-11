package com.example.kyosk.config;

import com.example.kyosk.model.Book;
import com.example.kyosk.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            // Clear existing data
            bookRepository.deleteAll();

            // Add new books
            bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 12.99));
            bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 14.99));
            bookRepository.save(new Book("1984", "George Orwell", 11.99));
            bookRepository.save(new Book("Pride and Prejudice", "Jane Austen", 9.99));
            bookRepository.save(new Book("The Hobbit", "J.R.R. Tolkien", 16.99));
            bookRepository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 13.99));
            bookRepository.save(new Book("Lord of the Flies", "William Golding", 10.99));
            bookRepository.save(new Book("Animal Farm", "George Orwell", 8.99));
            bookRepository.save(new Book("The Chronicles of Narnia", "C.S. Lewis", 19.99));
            bookRepository.save(new Book("Brave New World", "Aldous Huxley", 15.99));

            // Log message to confirm data initialization
            System.out.println("Database initialized with sample books!");

        };
    }
}
