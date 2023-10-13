package com.busanit.spring.b_autowire.config;

import com.busanit.spring.b_autowire.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ComponentScan(basePackages = {"domain","service"})
@Configuration
public class AppContext {
    @Bean
    @Primary  // 동일한 타입에서 가장 우선순위를 가짐
    @Qualifier("printer")
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

    @Bean
    public MemberPrinter memberPrinter3() {
        return new MemberPrinter();
    }

    @Bean
    MemberListPrinter memberListPrinter() {
        return new MemberListPrinter();
    }

    @Bean
    MemberInfoPrinter memberInfoPrinter() {
        /* Autowire를 통한 세터메서드 의존성 자동 주입
        // 세터를 통한 의존성 주입
        memberInfoPrinter.setMemberPrinter(memberPrinter());
        memberInfoPrinter.setMemberDao(memberDao());
         */
        return new MemberInfoPrinter();
    }

    // 기본 데이터 타입 값 설정하기
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();

        versionPrinter.setMajorVersion(6);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
