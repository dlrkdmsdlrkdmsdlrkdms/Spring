package com.busanit.spring.e_aop;

public class Main {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        CalculatorRe calculatorRe = new CalculatorRe();

        long start = System.nanoTime();
        calculator.factorial(30);
        long end = System.nanoTime();
        System.out.println("일반 실행시간 " +( end - start) );

        long start2 = System.nanoTime();
        calculatorRe.factorial(30);
        long end2 = System.nanoTime();
        System.out.println("재귀 실행시간 " +( end2 - start2) );

    }
}
