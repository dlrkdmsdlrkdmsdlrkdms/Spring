package com.busanit.spring.a_di_3장.main;

import com.busanit.spring.a_di_3장.config.AppContext5;
import com.busanit.spring.a_di_3장.domain.RegisterRequest;
import com.busanit.spring.a_di_3장.exception.DuplicateMemberException;
import com.busanit.spring.a_di_3장.exception.MemberNotFoundException;
import com.busanit.spring.a_di_3장.exception.WrongPasswordException;
import com.busanit.spring.a_di_3장.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainForSpring5 {

    private static AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) {

        // 스프링 컨테이너 생성
        // 2개 이상의 설정 정보 파일 사용하기
        context = new AnnotationConfigApplicationContext(AppContext5.class);

        // 사용자의 입력 받기
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("명령을 입력해주세요.");
            String command = scanner.nextLine();

            // exit : 종료
            // new : member를 새로 생성
            // change : 비밀번호를 변경
            if (command.equals("exit")) {
                break;
            }
            if (command.startsWith("new ")) {
                // 사용자로부터 인자를 공백 간격으로 입력 받음.
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if (command.equals("list")) {
                processListCommand();
                continue;
            } else if (command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if (command.equals("version")) {
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }

    private static void processVersionCommand() {
        // Bean 객체가 아닌 경우 getBean할 경우
        MemberPrinter bean = context.getBean(MemberPrinter.class);
        VersionPrinter versionPrinter = context.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void processInfoCommand(String[] args) {
        // info 외 인자가 1개만 존재해야 프로세스 진행
        if (args.length != 2) {
            return;
        }
        MemberInfoPrinter memberInfoPrinter = context.getBean("memberInfoPrinter", MemberInfoPrinter.class);

        // 2번째 인자정보 : email
        memberInfoPrinter.printMemberInfo(args[1]);
    }

    private static void processListCommand() {
        MemberListPrinter memberListPrinter = context.getBean("memberListPrinter", MemberListPrinter.class);
        memberListPrinter.printAll();
    }

    private static void printHelp() {
        System.out.println("명령어 사용법");
        System.out.println("exit : 프로그램이 종료됩니다.");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비밀번호 변경비밀번호");
        System.out.println("list : 저장된 회원 목록 보기");
        System.out.println("info 이메일 : 회원 정보 보기");
    }

    private static void processChangeCommand(String[] args) {
        if (args.length != 4) {
            return;
        }
        ChangePasswordService changePasswordService = context.getBean("changePasswordService", ChangePasswordService.class);

        try {
            changePasswordService.changePassword(args[1], args[2], args[3]);
            System.out.println("암호가 변경되었습니다.");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.");
        } catch (WrongPasswordException e) {
            System.out.println("암호가 틀립니다.");
        }
    }

    // 사용자로부터 new 로 입력받았을 때 => 회원가입 프로세스
    private static void processNewCommand(String[] args) {
        // 인자 : [0]new [1]이메일 [2]이름 [3]암호 [4]암호확인
        // 만약 인자 길이가 5개가 아니면 종료.
        if (args.length != 5) {
            return;
        }
        // 인자 길이가 유효하면, 회원 가입 서비스를 실행 => 조립기로부터 비즈니스 로직이 담긴 Service 객체 반환
        var memberRegisterService = context.getBean("memberRegisterService", MemberRegisterService.class);

        // 회원 가입 요청 객체에서 회원 가입 데이터를 받아
        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setEmail(args[1]);
        registerRequest.setName(args[2]);
        registerRequest.setPassword(args[3]);
        registerRequest.setConfirmPassword(args[4]);

        // 암호와 확인암호가 일치하지 않는 경우
        if (!registerRequest.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인암호가 일치하지 않습니다.");
            return;
        }

        try {
            // 회원 가입 서비스에서 등록
            memberRegisterService.register(registerRequest);
            System.out.println("성공적으로 등록 완료했습니다.");
            // 기존 회원 가운데 중복 이메일이 있는 경우
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일이 있습니다.");
        }



    }
}
