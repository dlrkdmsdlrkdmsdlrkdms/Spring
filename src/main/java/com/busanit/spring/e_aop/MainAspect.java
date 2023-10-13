package com.busanit.spring.e_aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Calculator calculator;
        calculator = context.getBean(Calculator.class);
        calculator.factorial(30);

        System.out.println(calculator.getClass().getName());
        context.close();

    }
}
