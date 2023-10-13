package com.busanit.spring.a_di_3장.config;

import com.busanit.spring.a_di_3장.domain.MemberDao;
import com.busanit.spring.a_di_3장.service.ChangePasswordService;
import com.busanit.spring.a_di_3장.service.MemberRegisterService;

// 각각의 클래스를 조립해주는 조립기
public class Assembler {
    // 세 가지 객체를 연결시켜 생성하고, Service 객체에 memberDao를 주입해줌.
    private MemberDao memberDao;
    private MemberRegisterService memberRegisterService;
    private ChangePasswordService changePasswordService;

    public Assembler() {
        memberDao = new MemberDao();
        // 생성자를 통해서 memberDao를 주입
        memberRegisterService = new MemberRegisterService(memberDao);
        changePasswordService = new ChangePasswordService();
        // 설정자(setter)를 통해서 memberDao를 주입
        changePasswordService.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return memberRegisterService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }
}
