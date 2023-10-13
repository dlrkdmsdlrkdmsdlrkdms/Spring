package com.busanit.spring.c_component.service;

import com.busanit.spring.c_component.domain.Member;
import com.busanit.spring.c_component.domain.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberInfoPrinter {
    /* 선택한 회원 정보만 출력하는 클래스 */

    private MemberDao memberDao;
    private MemberPrinter memberPrinter;

    // 의존성 세터 주입 (DI : setter 메서드)
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    // 의존성 세터 주입 (DI : setter 메서드)
    @Autowired
    public void setMemberPrinter(MemberPrinter memberPrinter) {
        this.memberPrinter = memberPrinter;
    }

//    public void normal() {
//        this.memberPrinter = memberPrinter;
//        this.memberDao = memberDao;
//    }
    // key(email)로 조회하여 출력
    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        // 이메일 조회 시 멤버가 없는 경우
        if (member == null) {
            System.out.println("데이터가 없습니다.");
            return;
        }
        // 있으면 출력
        memberPrinter.print(member);

    }

}
