package PokemonBeyond.MonsterBall.run;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.service.MonsterballService;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.MonsterBall.exception.EmptyNameexception;
import PokemonBeyond.Skill.aggregate.Skill;

import java.util.ArrayList;
import java.util.Scanner;


public class MonsterballApplication {
    private static String memberId;

    public void run(String Id, MonsterballService monsterballService) {
        Scanner sc = new Scanner(System.in);
        memberId = Id;
        ArrayList<MyPokemon> memberPokemon = monsterballService.showMyPokemon(memberId);
        int maxPokemonIndex;
        boolean isFirstOption = true;
        while (isFirstOption) {
            System.out.println("======== 내 포켓몬 =========");
            System.out.println("1. 포켓몬 리스트 조회하기");
            System.out.println("2. 포켓몬 이름 변경하기");
            System.out.println("3. 포켓몬 오박사님께 보내기");
            System.out.println("4. 이전 메뉴로 되돌아가기");
            System.out.println("원하는 메뉴를 선택하세요: ");
            int firstOption = sc.nextInt();
            switch (firstOption) {
                case 1:
                    boolean viewingList = true;
                    while (viewingList) {
                        System.out.println(monsterballService.inquiryMyPokemon(memberId));
                        maxPokemonIndex = monsterballService.getPokemonCount(memberId);
                        System.out.println("상세 정보를 보고 싶은 포켓몬의 번호를 입력해주세요(0번 -> 이전 메뉴로 돌아갑니다): ");

                        int choiceIdx = -1;
                        boolean invalidInput = false;
                        while (!invalidInput) {
                            try {
                                choiceIdx = Integer.parseInt(sc.next()) - 1;
                                invalidInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println(monsterballService.inquiryMyPokemon(memberId));
                                System.out.print("숫자를 입력해주세요: ");
                            }
                        }

                        if (choiceIdx == -1) viewingList = false;

                        else if (choiceIdx >= 0 && choiceIdx < maxPokemonIndex) {
                            MyPokemon selectedPokemon = monsterballService.selectMyPokemon(memberId, choiceIdx);
                            Pokemon pickedPokemon = selectedPokemon.getPokemon();
                            ArrayList<Skill> skillList = pickedPokemon.getPoekmonSkill();
                            System.out.println("포켓몬 종류: " + pickedPokemon.getPokemonName() +
                                    ", 이름: " + selectedPokemon.getName());
                            for (int i = 0; i < skillList.size(); i++) {
                                System.out.println((i + 1) + ". " + skillList.get(i).getSkillName() +
                                        " - 스킬위력: " + skillList.get(i).getSkillPower());
                            }
                            System.out.println("계속하려면 Enter키를 누르세요...");
                            sc.nextLine();
                            sc.nextLine();
                        } else {
                            System.out.println("잘못된 입력입니다. 다시 번호를 입력해주세요");
                        }
                    }
                    break;
                case 2:
                    boolean changingNames = true;
                    while (changingNames) {
                        System.out.println(monsterballService.inquiryMyPokemon(memberId));
                        maxPokemonIndex = monsterballService.getPokemonCount(memberId);
                        System.out.println("변경하실 포켓몬의 번호를 입력해주세요(0번 -> 이전 메뉴로 돌아갑니다): ");
                        int changeIdx = -1;
                        while (changeIdx < 0 || changeIdx >= maxPokemonIndex) {
                            try {
                                changeIdx = sc.nextInt() - 1;
                                if (changeIdx == -1) {
                                    changingNames = false;
                                    break;
                                }
                                if (changeIdx < 0 || changeIdx >= maxPokemonIndex) {
                                    System.out.println(monsterballService.inquiryMyPokemon(memberId));
                                    System.out.println("올바르지 않은 번호입니다. 다시 입력해주세요: ");
                                }
                            } catch (java.util.InputMismatchException e) {
                                System.out.println(monsterballService.inquiryMyPokemon(memberId));
                                System.out.println("숫자를 입력해주세요: ");
                                sc.next();
                            }
                        }
                        if (!changingNames) break;

                        System.out.println("변경하실 이름을 입력해주세요(변경을 취소하시려면 Enter를 눌러주세요): ");
                        sc.nextLine(); // 버퍼 비우기
                        String newName = sc.nextLine();
                        try {
                            if (newName.trim().equals("")) {
                                throw new EmptyNameexception("이름 변경을 취소하였습니다.");
                            }
                            monsterballService.changePokemonName(changeIdx, memberId, newName);
                        } catch (EmptyNameexception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    boolean viewingList3 = true;
                    while (viewingList3) {
                        System.out.println(monsterballService.inquiryMyPokemon(memberId));
                        maxPokemonIndex = monsterballService.getPokemonCount(memberId);
                        System.out.println("오박사님께 보내고 싶은 포켓몬의 번호를 입력해주세요(0번 -> 이전 메뉴로 돌아갑니다): ");

                        int deleteIdx = -1;
                        boolean invalidInput = false;
                        while (!invalidInput) {
                            try {
                                deleteIdx = sc.nextInt() - 1;
                                invalidInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println(monsterballService.inquiryMyPokemon(memberId));
                                System.out.print("숫자를 입력해주세요: ");
                            }
                        }

                        if (deleteIdx == -1) viewingList3 = false;

                        else if (deleteIdx >= 0 && deleteIdx < maxPokemonIndex) {
                            monsterballService.modifyPokemon(memberId, deleteIdx);
                            System.out.println("계속하려면 Enter키를 누르세요...");
                            sc.nextLine();
                            sc.nextLine();
                        } else {
                            System.out.println("잘못된 입력입니다. 다시 번호를 입력해주세요");
                        }
                    }
                    break;
                case 4:
                    // 포켓몬 조회를 나가면 리스트 자동저장 및 갱신
                    monsterballService.autoFileSave(memberId);
                    return;
            }
        }
    }
}
