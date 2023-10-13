package com.busanit.spring.f_db.main;

import com.busanit.spring.f_db.config.AppContext;
import com.busanit.spring.f_db.exception.MemberNotFoundException;
import com.busanit.spring.f_db.exception.WrongPasswordException;
import com.busanit.spring.f_db.service.ChangePasswordService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// ChangePasswordService
public class TestCPS {
    public static void main(String[] args) {
        // 스프링 컨테이너
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppContext.class);

        // 빈(비밀번호 변경) 객체 가져오기
        ChangePasswordService cps = context.getBean(ChangePasswordService.class);

        try {
            cps.changePassword("test@test.com", "1234", "123456789");
        } catch (MemberNotFoundException e){
            System.out.println("회원 정보가 없습니다.");
        } catch (WrongPasswordException e){
            System.out.println("비밀번호가 다릅니다.");
        }
        context.close();
    }
}
