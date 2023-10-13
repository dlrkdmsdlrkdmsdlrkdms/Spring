package com.busanit.spring.e_aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@EnableAspectJAutoProxy
public class AppContext {
    @Bean
    ExeTimeAspect exeTimeAspect(){
        return new ExeTimeAspect();
    }
    @Bean
    Calculator calculator(){
        return new CalculatorRe();
    }
    @Bean
    CalculatorImpl calculatorImpl(){
        return new CalculatorImpl();
    }
}
