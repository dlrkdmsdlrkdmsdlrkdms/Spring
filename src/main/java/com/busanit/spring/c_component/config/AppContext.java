package com.busanit.spring.c_component.config;

import com.busanit.spring.c_component.service.MemberPrinter;
import com.busanit.spring.c_component.service.VersionPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 애노테이션 기준으로 컴포넌트 스캔 제외
// @ComponentScan(basePackages = {"com.busanit.spring.c_component"},
// 클래스 기주능로 컴포넌트 스캔 제외
@ComponentScan(basePackages = {"com.busanit.spring.c_component"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ManualBean.class}))
@Configuration
public class AppContext {

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
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
