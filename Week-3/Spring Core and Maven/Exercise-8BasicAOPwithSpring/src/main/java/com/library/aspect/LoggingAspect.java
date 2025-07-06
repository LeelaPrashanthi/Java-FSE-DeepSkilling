package com.library.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.processBook(..))")
    public void logBefore() {
        System.out.println("🚀 [AOP] Before executing processBook()");
    }

    @After("execution(* com.library.service.BookService.processBook(..))")
    public void logAfter() {
        System.out.println("✅ [AOP] After executing processBook()");
    }
}
