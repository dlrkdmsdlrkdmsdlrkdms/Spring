package com.busanit.spring.a_di_3장.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(AppContext2.class)
@Import({AppContext1.class, AppContext2.class})
@Configuration
public class AppContext3 {
}
