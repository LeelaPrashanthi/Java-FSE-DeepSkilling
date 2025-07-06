package com.library;

import com.library.service.BookService;
import com.library.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Running Exercise 1: Basic Spring Setup");

        BookRepository repo = context.getBean("bookRepository", BookRepository.class);
        BookService service = context.getBean("bookService", BookService.class);

        repo.save();
        service.processBook();
    }
}
