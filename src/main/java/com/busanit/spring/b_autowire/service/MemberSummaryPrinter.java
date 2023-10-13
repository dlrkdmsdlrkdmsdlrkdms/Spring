package com.busanit.spring.b_autowire.service;

import com.busanit.spring.b_autowire.domain.Member;

// MemberPrinter를 상속받는 클래스
public class MemberSummaryPrinter extends MemberPrinter {
    @Override
    public void print(Member member) {
        System.out.printf("회원정보: 이메일=%s, 이름=%s\n",
                member.getEmail(),
                member.getName());
    }
}
