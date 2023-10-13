package com.busanit.spring.f_db.config;

import com.busanit.spring.f_db.domain.MemberDao;
import com.busanit.spring.f_db.service.*;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppContext {

    // 데이터 소스
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        // 커넥션 풀 초기 개수
        dataSource.setInitialSize(10);
        // 커넥션 풀 최대
        dataSource.setMaxActive(100);
        return dataSource;
    }

    // 트랜잭션 관리자 설정
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new
                DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    // @Bean 객체와 싱글톤
    // memberDao()는 다른 메서드에서 여러번 호출된다.
    // 호출될때마다 new 키워드로 생성이 되어 리턴 된다.
    // 다른 객체? => 각각의 리턴된 객체들은 모두 싱글톤 객체
    // 스프링 컨테이너는 @Bean 어노테이션이 있는 메서드에 대해
    // 단 하나의 객체만 생성한다.

    // Dao 의존성 주입
    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegisterService() {
        // 생성자를 통해서 의존 Bean 객체(memberDao())를 주입
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        var changePasswordService = new ChangePasswordService();
        // 세터를 통해서 의존 Bean 객체(memberDao())를 주입
        changePasswordService.setMemberDao(memberDao());
        return changePasswordService;
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    MemberListPrinter memberListPrinter() {
        // 생성자를 통한 의존성 주입
        return new MemberListPrinter(memberPrinter(), memberDao());
    }

    @Bean
    MemberInfoPrinter memberInfoPrinter() {
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        // 세터를 통한 의존성 주입
        memberInfoPrinter.setMemberPrinter(memberPrinter());
        memberInfoPrinter.setMemberDao(memberDao());
        return memberInfoPrinter;
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
