package PokemonBeyond.run;

import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.service.MemberService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
//        MemberService memberService = new MemberService();
        Scanner sc = new Scanner(System.in);
        boolean isFirstMenu = true;
        while(isFirstMenu){
            System.out.println("=====PokemonBeyond=====");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            System.out.print("원하는 메뉴를 선택하세요: ");
            int firstMenu = sc.nextInt();
            switch (firstMenu){
                case 1 :
                    sc.nextLine();
                    System.out.println("회원가입에 필요한 정보를 입력하세요.");
                    System.out.print("아이디: ");
                    String registId = sc.nextLine();
                    System.out.print("비밀번호: ");
                    String registPwd = sc.nextLine();
                    System.out.print("이름: ");
                    String registName = sc.nextLine();
                    System.out.print("나이: ");
                    int registAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("닉네임: ");
                    String registNickname = sc.nextLine();

//                    memberService.registMember(new Member(registId,registPwd,registName,registAge,registNickname));
                    isFirstMenu = false;
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("로그인에 필요한 정보를 입력하세요.");
                    System.out.print("아이디: ");
                    String loginId = sc.nextLine();
                    System.out.print("비밀번호: ");
                    String loginPwd = sc.nextLine();

//                    memberService.loginMember(loginId,loginPwd);
                    isFirstMenu = false;
                    break;
                case 3:
                    return;
            }

            boolean isSecondMenu = true;
            while(isSecondMenu){
                System.out.println("===== Game Menu =====");
                System.out.println("1. 도감");
                System.out.println("2. 내 포켓몬");
                System.out.println("3. 야생");
                System.out.println("===== Community Menu =====");
                System.out.println("4. 내 회원정보");
                System.out.println("5. 게시판");
                System.out.println("6. 친구목록");
                System.out.println("===== 게임 종료를 원하신다면 7을 입력하세요 =====");
                System.out.print("원하는 메뉴를 선택하세요: ");
                int secondMenu = sc.nextInt();
                sc.nextLine();
                switch (secondMenu){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        return;

                }

            }
        }
    }
}
