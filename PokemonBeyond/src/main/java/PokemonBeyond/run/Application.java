package PokemonBeyond.run;

import PokemonBeyond.Encyclopedia.service.EncyclopediaService;
import PokemonBeyond.Member.aggregate.Member;
import PokemonBeyond.Member.service.MemberService;
import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.run.MonsterballApplication;
import PokemonBeyond.MonsterBall.service.MonsterballService;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;
import PokemonBeyond.Skill.aggregate.Skill;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    static final PokemonService pokemonService = new PokemonService();
    static final EncyclopediaService encyclopediaService = new EncyclopediaService();
    static final MonsterballService monsterballService = new MonsterballService();
    static final MemberService memberService = new MemberService();
    static final MonsterballApplication monsterballApplication = new MonsterballApplication();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String loginId = null;
        Member resultMember = null;

        registOrLogin(sc,resultMember,loginId);

            boolean isSecondMenu = true;
            int encyclopediaMenu = 0;

            while(isSecondMenu){
                System.out.println("===== Game Menu =====");
                System.out.println("1. 도감");
                System.out.println("2. 내 포켓몬");
                System.out.println("3. 야생");
                System.out.println("===== Community Menu =====");
                System.out.println("4. 회원관리");
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
                    case 2:   ///// monsterballApplication
                        monsterballApplication.run(loginId);
                        break;
                    case 3:
                        goToBush(isthirdMenu,sc,loginId);
                        break;
                    case 4:
                        goMyPage(resultMember);
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

    private static void registOrLogin(Scanner sc,Member resultMember, String loginId) {
        boolean isFirstMenu = true;
        while (isFirstMenu) {
            System.out.println("====== 회원 관리 프로그램 ======");
            System.out.println("1. 회원 가입");
            System.out.println("2. 로그인");
            System.out.print("메뉴를 선택해 주세요: ");
            int input = sc.nextInt();

            switch (input) {
                case 1: // 회원가입
                    String registId = memberService.registMember(signUp());
                    ArrayList<MyPokemon> newMyPokemon = monsterballService.createNewMembersList(registId);
                    System.out.println(newMyPokemon); // 여기서 만든게 도감에는 저장되는데 내포켓몬에 이상하게 저장됨
                    encyclopediaService.addEncyclopedia(registId, newMyPokemon.get(0).getPokemon());

                    break;
                case 2: // 로그인
                    resultMember = memberService.logInMember(checkMemIdAndPwd());
                    if (resultMember != null) {
                        System.out.println("로그인 되었습니다!");
                        isFirstMenu = false;
                        loginId = resultMember.getId();
                    }
                    break;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }

    private static void goMyPage(Member resultMember) {
        boolean isMemberMenu = true;
        Scanner sc = new Scanner(System.in);
        while (isMemberMenu) {
            System.out.println("\n===== 회원 관리 =====");
            System.out.println("1. 회원 정보 보기");
            System.out.println("2. 회원 수정");
            System.out.println("3. 회원 찾기");
            System.out.println("4. 회원 탈퇴");
            System.out.println("5. 뒤로가기");
            System.out.print("메뉴를 선택해 주세요: ");

            int input = sc.nextInt();

            switch (input) {
                case 1:
                    goViewMember(resultMember);
                    break;
                case 2: memberService.modifyMember(reform(resultMember)); break;
                case 3:
                    sc.nextLine();
                    System.out.println("회원 닉네임을 입력하세요: ");
                    String nickName = sc.nextLine();
                    Member searchedMember = memberService.searchMember(nickName);
                    System.out.println("결과: " + searchedMember.getNickName());
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("정말로 탈퇴하시겠습니까?(Y/N): ");
                    while(true){
                        String answer = sc.nextLine().toUpperCase();
                        if(answer.equals("Y")){
                            memberService.exitMember(resultMember);
                            return;
                        } else if (answer.equals("N")){
                            break;
                        } else {
                            System.out.println("잘못 입력하셨습니다.");
                            break;
                        }
                    }
                    break;
                case 5:
                    isMemberMenu = false;
                    break;         // 다른거 더 들어갔다가 로그아웃하면 바로 로그아웃 안된다...
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }

    private static Member reform(Member resultMember) {
        Member modifiedMember = resultMember;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== 수정할 목록 ====");
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
        System.out.println();
        String answer = sc.nextLine().toUpperCase();
        if (answer.equals("Y")) {
            // 그냥 돌아간다.
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

    private static void goToBush(boolean isthirdMenu,Scanner sc,String loginId) {
        int wildMenu;
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
                    System.out.print("포켓몬을 선택해주세요: ");
                    System.out.println(monsterballService.inquiryMyPokemon(loginId));
                    int toFightPokemon = sc.nextInt();
                    ArrayList<MyPokemon> myPokemons = monsterballService.showMyPokemon(loginId);
                    Pokemon fightingPokemon = myPokemons.get(toFightPokemon - 1).getPokemon();
                    System.out.println(fightingPokemon.getPokemonName() + "의 스킬 목록");
                    ArrayList<Skill> skills = fightingPokemon.getPoekmonSkill();
                    for (int i = 0; i < skills.size(); i++) {
                        System.out.println((i + 1) + ". " + skills.get(i).getSkillName()
                                + "( Power : " + skills.get(i).getSkillPower() + " )");
                    }
                    System.out.print("사용할 스킬을 선택해주세요: ");
                    int fightSkillNo = sc.nextInt();
                    if (pokemonService.isWildPokemonWin(meetPokemon, fightingPokemon, fightSkillNo)) {
                        System.out.println(fightingPokemon.getPokemonName() + "은(는) 쓰러졌다!");
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
                        System.out.println();
                    }
                    break;
                case 2:
                    isthirdMenu = false;
                    System.out.println("무사히 도망쳤다!");
                    break;
            }
        }
    }
}

