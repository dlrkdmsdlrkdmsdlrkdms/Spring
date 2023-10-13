package com.busanit.spring.e_aop;

public class CalculatorImpl implements Calculator{
    @Override
    public long factorial(long num) {
        // 성능ㅇ르 계산하는 로직
        /*
        long start = System.currentTimeMillis();

        long result = 1;
        for(int i = 0; i<=num; i++){
            result *= i;
        }
        long end = System.currentTimeMillis();
        System.out.printf("Calculatorlmpl 실행시간 : %d\n", end-start);
        return result;
        */
        long result = 1;
        for (int i = 0; i <= num; i++) {
            result *= i;
        }
        return result;

    }
}
