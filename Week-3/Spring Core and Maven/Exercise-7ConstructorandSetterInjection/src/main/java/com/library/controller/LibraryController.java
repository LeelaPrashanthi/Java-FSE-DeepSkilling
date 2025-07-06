package com.library.controller;

import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/saveBook")
    public String saveBook() {
        bookService.processBook();
        return "Book Processed Successfully!";
    }
}
