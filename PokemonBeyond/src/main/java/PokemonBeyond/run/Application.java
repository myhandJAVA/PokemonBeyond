package PokemonBeyond.run;

import PokemonBeyond.Encyclopedia.service.EncyclopediaService;
import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.service.MemberService;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;

import java.util.Scanner;

public class Application {
    static final PokemonService pokemonService = new PokemonService();
    static final EncyclopediaService encyclopediaService = new EncyclopediaService();
    //   static final MemberService memberService = new MemberService();

    public static void main(String[] args) {
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
                        System.out.println("===== 도감 =====");
                        System.out.println("1. 전체 회원 도감 조회");
                        System.out.println("2. 도감 상세 조회");
                        System.out.println("3. 도감 삭제");
                        System.out.println("4. 뒤로 가기");
                        System.out.print("원하는 메뉴를 선택하세요: ");
                        int encyclopediaMenu = sc.nextInt();
                        break;
                    case 2:
                        System.out.println("===== 내 포켓몬 =====");
                        System.out.println("1. ???");
                        System.out.print("원하는 메뉴를 선택하세요: ");
                        int myPokemonMenu = sc.nextInt();
                        break;
                    case 3:
                        System.out.println("===== 야생 =====");
                        System.out.println("야생 포켓몬을 잡으러 갑니다.");
                        Pokemon meetPokemon = pokemonService.meetRandomPokemon();
                        System.out.println("앗! 야생 " + meetPokemon.getPokemonName() + " 이(가) 튀어나왔다!");
                        System.out.println("무엇을 할까?");
                        System.out.println("1. 싸운다.");
                        System.out.println("2. 도망친다.");
                        System.out.print("메뉴 입력: ");
                        int wildMenu = sc.nextInt();
                        break;
                    case 4:
                        System.out.println("===== 내 회원정보 =====");
                        System.out.println("1. ???");
                        System.out.print("메뉴 입력: ");
                        int memberInformationMenu = sc.nextInt();
                        break;
                    case 5:
                        System.out.println("===== 게시판 =====");
                        System.out.println("1. ???");
                        System.out.print("메뉴 입력: ");
                        int communityMenu = sc.nextInt();
                        break;
                    case 6:
                        System.out.println("====== 친구목록 =====");
                        System.out.println("1. ???");
                        System.out.print("메뉴 입력: ");
                        int friendMenu = sc.nextInt();
                        break;
                    case 7:
                        return;

                }

            }
        }
    }
}
