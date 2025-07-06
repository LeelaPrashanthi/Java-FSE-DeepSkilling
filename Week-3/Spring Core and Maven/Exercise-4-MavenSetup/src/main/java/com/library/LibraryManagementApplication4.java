package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication4{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Exercise 4 → Spring Project Setup Successful!");

        BookService service = context.getBean("bookService", BookService.class);
        service.processBook();
    }
}
