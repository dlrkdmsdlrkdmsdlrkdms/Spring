package com.busanit.spring.f_db.main;


import com.busanit.spring.f_db.config.AppContext;
import com.busanit.spring.f_db.domain.Member;
import com.busanit.spring.f_db.domain.MemberDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Collection;

public class MainForDao {
    private  static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppContext.class);

        memberDao = context.getBean(MemberDao.class);

        insertMember();
        //updateMember();
        selectAll();
        selectByEmail();
        context.close();
    }

    private static void insertMember() {
        System.out.println("===============MainForDao.insertMember=====================");
        Member member = new Member("abcd@test.com","1234","Kei",
                LocalDateTime.now());
        memberDao.insert(member);
        System.out.println("멤버 데이터 추가");
    }


    private static void updateMember() {
        System.out.println("==========MainForDao.update===============");
        // SQL문으로 조회한 (DAO) 에서 가져온 멤버 객체 비밀번호 변경 시 정상 업데이트 되는지 확인
        Member member = memberDao.selectByEmail("test2@test2.com");
        member.changePassword("12345","123456");
        memberDao.update(member);

        Member changeMember = memberDao.selectByEmail("test2@test2.com");
        memberDao.update(member);
    }

    // 전체 조회 테스트
    private static void selectAll(){

        Collection<Member> members = memberDao.selectAll();
        for (Member member : members) {
            System.out.println(member.getEmail() + " " + member.getName());
        }
    }


    // select 개별 테스트
    private  static void selectByEmail(){
        System.out.println("=====MainForDao.selectAll=====");
        Member member = memberDao.selectByEmail("test@test.com");
        System.out.println(member);
        System.out.println(member.getEmail() + " " + member.getName());

    }

}
