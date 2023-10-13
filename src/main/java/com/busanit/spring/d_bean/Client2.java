package com.busanit.spring.d_bean;


// 빈의 생명주기
// InitializingBean :  빈 객체가 초기화 될 때
// DisposableBean : 빈 객체가 소멸할 때
public class Client2{

    private String host;
    public void connect() {
        System.out.println("Client2.connect");
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void send() {
        System.out.println("클라이언트2 send 호출" + host);
    }

    public void close() {
        System.out.println("Client2.close");
    }

}
