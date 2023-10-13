package com.busanit.spring.d_bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 컨테이너 생성과 함께 + 빈 객체 생ㅅ어 + 초기 메서드
        var context
                = new AnnotationConfigApplicationContext(AppContext.class);

        // Bean 객체 사용
       Client client = context.getBean(Client.class);
        Client2 client2 = context.getBean(Client2.class);
        client.send();

        // 컨테이너 종료와 함께 소멸 메서드 호출
        context.close();
    }
}
