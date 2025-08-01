package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBook() {
        System.out.println("Exercise 4 → Processing book in service...");
        bookRepository.save();
    }
}
