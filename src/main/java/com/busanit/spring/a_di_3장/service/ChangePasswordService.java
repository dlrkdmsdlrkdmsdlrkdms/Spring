package com.busanit.spring.a_di_3장.service;

import com.busanit.spring.a_di_3장.exception.MemberNotFoundException;
import com.busanit.spring.a_di_3장.domain.Member;
import com.busanit.spring.a_di_3장.domain.MemberDao;

// 관심사 : 비밀번호를 바꾸는 것
public class ChangePasswordService {

    private MemberDao memberDao;

    // 비밀번호 변경
    public void changePassword(String email, String oldPassword, String newPassword) {
        Member member = memberDao.selectByEmail(email);
        // 1. 해당 email이 존재하는가 => if not => Exception
        if (member == null) {
            throw new MemberNotFoundException();
        }
        // 2. else 비밀번호 변경해라.
        member.changePassword(oldPassword, newPassword);
        // 3. dao를 통해 데이터에 반영해라.
        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
