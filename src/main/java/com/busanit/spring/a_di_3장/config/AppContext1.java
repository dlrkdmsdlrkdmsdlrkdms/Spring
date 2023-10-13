package com.busanit.spring.a_di_3장.config;

import com.busanit.spring.a_di_3장.domain.MemberDao;
import com.busanit.spring.a_di_3장.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext1 {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}
