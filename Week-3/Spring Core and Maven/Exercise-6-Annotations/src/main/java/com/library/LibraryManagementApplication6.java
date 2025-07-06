package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.library.config.AppConfig;

public class LibraryManagementApplication6 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Exercise 6 → Annotation-Based Configuration Successful!");

        BookService service = context.getBean(BookService.class);
        service.processBook();
    }
}
