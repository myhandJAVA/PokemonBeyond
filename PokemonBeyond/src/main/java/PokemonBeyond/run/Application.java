package PokemonBeyond.run;

import PokemonBeyond.Encyclopedia.service.EncyclopediaService;
import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.service.MemberService;
import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.service.MonsterballService;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    static final PokemonService pokemonService = new PokemonService();
    static final EncyclopediaService encyclopediaService = new EncyclopediaService();
        static final MonsterballService monsterballService = new MonsterballService();
    static final MemberService memberService = new MemberService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isFirstMenu = true;
        String loginId = null;

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
                    memberService.registMember(new Member(registId,registPwd,registName,registAge,registNickname));
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("로그인에 필요한 정보를 입력하세요.");
                    System.out.print("아이디: ");
                    loginId = sc.nextLine();
                    System.out.print("비밀번호: ");
                    String loginPwd = sc.nextLine();
                    Member loginedMember = memberService.logInMember(new Member(loginId,loginPwd));
                    if (loginedMember!=null)  isFirstMenu = false;

                    break;
                case 3:
                    return;
            }

            boolean isSecondMenu = true;
            int encyclopediaMenu = 0;
            int myPokemonMenu = 0;
            int wildMenu = 0;
            int memberInformationMenu = 0;
            int communityMenu = 0;
            int friendMenu = 0;
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

                boolean isthirdMenu = true;
                switch (secondMenu) {
                    case 1:
                        while (isthirdMenu) {
                            System.out.println("===== 도감 =====");
                            System.out.println("1. 전체 회원 도감 조회");
                            System.out.println("2. 도감 상세 조회");
                            System.out.println("3. 뒤로 가기");
                            System.out.print("원하는 메뉴를 선택하세요: ");
                            encyclopediaMenu = sc.nextInt();
                            switch (encyclopediaMenu) {
                                case 1:
                                    System.out.println("전체 회원의 도감을 조회합니다.");
                                    encyclopediaService.findAllEncyclopedia();
                                    break;
                                case 2:
                                    System.out.println("회원의 도감을 상세 조회합니다.");
                                    System.out.print("조회할 회원의 아이디를 입력하세요: ");
                                    String toFindEncycMemId = sc.next();
                                    encyclopediaService.findEncyclopedia(toFindEncycMemId);
                                    break;
                                case 3:
                                    isthirdMenu = false;
                            }
                        }

                        break;
                    case 2:
                        System.out.println("===== 내 포켓몬 =====");
                        System.out.println("1. 내 포켓몬 조회");
                        System.out.println("2. 오박사님께 보내기");
                        System.out.println("3. 이름 짓기");
                        System.out.println("4. 내 포켓몬 스킬 조회");
                        System.out.print("원하는 메뉴를 선택하세요: ");
                        myPokemonMenu = sc.nextInt();
                        break;
                    case 3:
                        while (isthirdMenu) {
                            System.out.println("===== 야생 =====");
                            Pokemon meetPokemon = pokemonService.meetRandomPokemon();
                            System.out.println("앗! 야생 " + meetPokemon.getPokemonName() + " 이(가) 튀어나왔다!");
                            System.out.println("무엇을 할까?");
                            System.out.println("1. 싸운다.");
                            System.out.println("2. 도망친다.");
                            System.out.print("메뉴 입력: ");
                            wildMenu = sc.nextInt();
                            switch (wildMenu) {
                                case 1:
                                    ArrayList<MyPokemon> fightingPokemonList = monsterballService.showMyPokemons(loginId);
                                    System.out.print("포켓몬을 선택해주세요: ");
                                    int toFightPokemon = sc.nextInt();
                                    MyPokemon fightingPokemon = fightingPokemonList.get(toFightPokemon);
                                    System.out.println(fightingPokemon.getPokemon().getPokemonName() + "의 스킬 목록");
                                    System.out.println(fightingPokemon.getPokemon().getPoekmonSkill());
                                    System.out.print("사용할 스킬을 선택해주세요: ");
                                    int fightSkillNo = sc.nextInt();
                                    if (pokemonService.isWildPokemonWin(meetPokemon, fightingPokemon.getPokemon(), fightSkillNo)) {
                                        System.out.println(fightingPokemon.getPokemon().getPokemonName() + "은(는) 쓰러졌다!");
                                    } else {
                                        System.out.println("야생의 " + meetPokemon.getPokemonName() + "은(는) 쓰러졌다!");
                                        System.out.println("신난다! " + meetPokemon.getPokemonName() + "을(를) 잡았다!");
                                        ArrayList<Integer> myPokemonNoList = encyclopediaService
                                                .getEncyclopedia(loginId).getPokemonNoInEncyclopedia();
                                        boolean isInEncyc = false;
                                        for (int pokemonNo : myPokemonNoList) {
                                            if (pokemonNo == meetPokemon.getPokemonNo()) isInEncyc = true;
                                        }
                                        if (!isInEncyc) {
                                            encyclopediaService.addPokemonToEncylopedia(loginId, meetPokemon.getPokemonNo());
                                        }
                                        //스킬추가 추가 - 꽉차면 삭제
                                        break;
                                    }
                                case 2:
                                    isthirdMenu = false;
                                    break;
                            }
                        }
                                break;
                                case 4:
                                    System.out.println("===== 내 회원정보 =====");
                                    System.out.println("1. 내 정보 조회");
                                    System.out.println("2. 내 정보 수정");
                                    System.out.println("3. 회원 탈퇴");
                                    System.out.print("메뉴 입력: ");
                                    memberInformationMenu = sc.nextInt();
                                    break;
                                case 5:
                                    System.out.println("===== 게시판 =====");
                                    System.out.println("1. 게시글 조회");
                                    System.out.println("2. 게시글 작성");
                                    System.out.println("3. 게시글 수정");
                                    System.out.println("4. 게시글 삭제");
                                    System.out.print("메뉴 입력: ");
                                    communityMenu = sc.nextInt();
                                    break;
                                case 6:
//                                    System.out.println("====== 친구목록 =====");
//                                    System.out.println("1. ???"); // 조회 추가 삭제
//                                    System.out.print("메뉴 입력: ");
//                                    friendMenu = sc.nextInt();
                                    break;
                                case 7:
                                    return;

                }
            }


        }
    }
}
