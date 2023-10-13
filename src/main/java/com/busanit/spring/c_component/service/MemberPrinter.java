package com.busanit.spring.c_component.service;

import com.busanit.spring.c_component.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
@Component
// SRP 적용 : 단일책임 원칙
public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

      @Autowired(required = false)
      public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
          this.dateTimeFormatter = dateTimeFormatter;
      }


    // Optional : 자바 8버전 부터 사용되는 래퍼 클래스 기본 타입을 제네릭으로 가지고 있으며, null인
    // 경우에도 NPE을 방지하기 위해 사용하는 클래스
    // 값이 null이 아닐 경우 isPresent():true .get()을 통해서 값을 가져올 수 있다.
    // Autowired에서도 Optional을 null로 판단하지 않기 때문에 예외가 발생하지 않는다.
/*
    @Autowired(required = false)
    public void setDateTimeFormatter(Optional<DateTimeFormatter> optional) {
        if (optional.isPresent()) {
            this.dateTimeFormatter = optional.get();
        } else {
            // optional.isEmpty();
            this.dateTimeFormatter = null;
        }
    }

 */

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    // null일 경우에도 예외발행하지 않고, 존재하지 않으면 메서드 자체를 호출하지 않는다.
    /*
    @Autowired
    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

     */



    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf("회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    member.getRegisterDateTime());
        } else {
            System.out.printf("회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }
}
