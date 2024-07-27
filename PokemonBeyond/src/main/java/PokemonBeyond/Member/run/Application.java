package PokemonBeyond.Member.run;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.service.MemberService;

import java.util.Scanner;

public class Application {

    // 프로그램 켜자마자 실행되면서 객체 생성
    private static final MemberService ms = new MemberService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("====== 회원 관리 프로그램 ======");
            System.out.println("1. 회원 가입");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();

            switch (input) {
                case 1: ms.registMember(signUp()); break;


            }
        }


    }

    private static Member signUp() {
        Member newMember = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine();

        System.out.print("패스워드를 입력하세요: ");
        String pwd = sc.nextLine();

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();

        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt();

        sc.nextLine();      // 버퍼의 개행문자 처리

        System.out.print("닉네임을 입력하세요: ");
        String nickName = sc.nextLine();

        newMember = new Member(id, pwd, name, age, nickName);

        return newMember;
    }
}
