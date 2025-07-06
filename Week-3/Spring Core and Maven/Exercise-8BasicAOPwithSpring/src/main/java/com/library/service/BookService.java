package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void processBook() {
        System.out.println("📚 Processing book...");
        bookRepository.save();
    }
}
