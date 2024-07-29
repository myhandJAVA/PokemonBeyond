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
            System.out.println("2. 로그인");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();

            switch (input) {
                case 1: ms.registMember(signUp()); break;
                case 2:
                    Member resultMember = ms.logInMember(checkMemIdAndPwd());
                    if (resultMember != null) {
                        goMyPage(resultMember);
                    }
                    break;
            }
        }
    }

    private static void goMyPage(Member resultMember) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== 회원 관리 =====");
            System.out.println("1. 회원 정보 보기");
            System.out.println("2. 회원 수정");
            System.out.println("3. 회원 찾기");
            System.out.println("4. 회원 탈퇴");
            System.out.println("5. 로그아웃");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();

            switch (input) {
                case 1:
//                    ms.viewMember(resultMember); break;
                    goViewMember(resultMember);
                case 2: ms.modifyMember(reform(resultMember)); break;
                case 3: ms.findMemberBy(chooseMemNickName()); break;
                case 4:
                    sc.nextLine();
                    System.out.print("정말 삭제하시겠습니까?(Y/N): ");
                    String answer = sc.nextLine().toUpperCase();
                    if (answer.equals("Y")) {
                        ms.removeMember(resultMember);
                        return;
                    } else if (answer.equals("N")) {
                        goMyPage(resultMember);
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                    break;
                case 5: return;         // 다른거 더 들어갔다가 로그아웃하면 바로 로그아웃 안된다...
            }
        }
    }

    private static Member reform(Member resultMember) {
        Member modifiedMember = resultMember;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("==== 수정할 목록 ====");
            System.out.println("1. 패스워드");
            System.out.println("2. 닉네임");
            System.out.println("3. 나이");
            System.out.println("4. 메인 메뉴로 돌아가기");
            System.out.print("수정할 항목을 선택해주세요: ");
            int chooseNo = sc.nextInt();
            sc.nextLine();
            switch (chooseNo) {
                case 1:
                    System.out.print("수정 할 패스워드를 입력하세요: ");
                    modifiedMember.setPwd(sc.nextLine());
                    break;
                case 2:
                    System.out.print("수정 할 닉네임을 입력하세요: ");
                    modifiedMember.setNickName(sc.nextLine());
                    break;
                case 3:
                    System.out.print("수정 할 나이를 입력하세요: ");
                    modifiedMember.setAge(sc.nextInt());
                    break;
                case 4:
                    System.out.print("메인 메뉴로 돌아갑니다.");
                    // 어떻게 돌리지??
                default:
                    System.out.print("번호를 다시 제대로 입력해 주세요: ");
            }

            return modifiedMember;
        }
    }

    private static void goViewMember(Member resultMember) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 내 정보 보기 =====");
        System.out.println("닉네임: " + resultMember.getNickName());
        System.out.println("이름: " + resultMember.getName());
        System.out.println("나이: " + resultMember.getAge());
        System.out.println();
        System.out.print("돌아가시겠습니까?(Y/N): ");
        String answer = sc.nextLine().toUpperCase();
        if (answer.equals("Y")) {
            goMyPage(resultMember);
        } else if (answer.equals("N")) {
            goViewMember(resultMember);
        } else {
            System.out.println("잘못 입력하셨습니다.");
            goViewMember(resultMember);
        }
    }

    private static Member chooseMemNickName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("찾고 싶은 회원의 닉네임을 입력하세요: ");
        String nickName = sc.nextLine();

        return new Member(nickName);
    }

    private static Member checkMemIdAndPwd() {
        Member logInMember = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요: ");
        String id = sc.nextLine();

        System.out.print("패스워드를 입력하세요: ");
        String pwd = sc.nextLine();

        logInMember = new Member(id, pwd);

        return logInMember;
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
