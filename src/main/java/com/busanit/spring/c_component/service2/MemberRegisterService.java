package com.busanit.spring.c_component.service2;

import com.busanit.spring.c_component.domain.Member;
import com.busanit.spring.c_component.domain.MemberDao;
import com.busanit.spring.c_component.domain.RegisterRequest;
import com.busanit.spring.c_component.exception.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

// @ManualBean
// 회원 등록 서비스, 서비스 레이어, 관심사 : 회원 등록
public class MemberRegisterService {
    // 데이터 접근 객체를 필드로 컴포지션
    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {
    }

    // 생성자를 통해서 의존 객체를 주입 받음
    public MemberRegisterService(MemberDao memberDao) {
        // 주입 받은 객체를 필드에 할당
        this.memberDao = memberDao;
    }

    // 회원 등록 로직
    public Long register(RegisterRequest request) {
        // 기존 멤버가 있는지 확인
        // 주입 받은 memberDao 의존 객체를 사용
        Member member = memberDao.selectByEmail(request.getEmail());
        if (member != null) {
            // 에러 발생
            throw new DuplicateMemberException("email이 중복되었습니다. " + request.getEmail());
        }

        // 없으면 새로운 멤버 생성
        Member newMember = new Member(request.getEmail(), request.getPassword(), request.getName(), LocalDateTime.now());

        memberDao.insert(newMember);
        return newMember.getId();

    }
}
