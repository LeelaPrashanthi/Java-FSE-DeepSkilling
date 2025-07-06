package com.library;

import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySpringBootApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(LibrarySpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Spring Boot Application Started Successfully!");
        bookService.processBook();   // This will print "Processing book..." etc.
    }
}

