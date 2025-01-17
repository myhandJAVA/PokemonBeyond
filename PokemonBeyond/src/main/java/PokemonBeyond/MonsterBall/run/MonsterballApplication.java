package PokemonBeyond.MonsterBall.run;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.service.MonsterballService;
import PokemonBeyond.Skill.aggregate.Skill;

import java.util.ArrayList;
import java.util.Scanner;


public class MonsterballApplication {
    private static String memberId;
    public void run(String Id,MonsterballService monsterballService) {
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
            System.out.print("원하는 메뉴를 선택하세요: ");
            int firstOption = sc.nextInt();
            switch (firstOption) {
                case 1:
                        System.out.println(monsterballService.inquiryMyPokemon(memberId));
                        maxPokemonIndex = monsterballService.getPokemonCount(memberId);
                        int choiceIdx = -1;
                        while (choiceIdx < 0 || choiceIdx >= maxPokemonIndex) {
                            System.out.print("상세 정보를 보고싶은 포켓몬의 번호를 입력해주세요 : ");
                            try {
                                choiceIdx = sc.nextInt() - 1;
                                if (choiceIdx < 0 || choiceIdx >= maxPokemonIndex) {
                                    System.out.println("올바르지 않은 번호입니다. 다시 입력해주세요.");
                                }
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("숫자를 입력해주세요.");
                                sc.next();
                            }
                        }
                        MyPokemon selectedPokemon = monsterballService.selectMyPokemon(memberId, choiceIdx);
                        System.out.println("포켓몬 이름: "+selectedPokemon.getPokemon().getPokemonName());
                        System.out.println("포켓몬 별명: " + selectedPokemon.getName());
                    System.out.println("공격력: " + selectedPokemon.getPokemon().getPokemonPower());
                    System.out.print("보유 스킬: ");
                    for (int i = 0; i < selectedPokemon.getPokemon().getPoekmonSkill().size(); i++) {
                        Skill selectedSkill = selectedPokemon.getPokemon().getPoekmonSkill().get(i);
                        System.out.print((i+1) +". "+selectedSkill.getSkillName()+" ( 공격력: "+selectedSkill.getSkillPower()+" ) || ");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println(monsterballService.inquiryMyPokemon(memberId));
                    int changeIdx = -1;
                    maxPokemonIndex = monsterballService.getPokemonCount(memberId); // 포켓몬 수를 가져오는 메서드

                    while (changeIdx < 0 || changeIdx >= maxPokemonIndex) {
                        System.out.print("변경하실 포켓몬의 번호를 입력해주세요 : ");
                        try {
                            changeIdx = sc.nextInt() - 1;
                            if (changeIdx < 0 || changeIdx >= maxPokemonIndex) {
                                System.out.println("올바르지 않은 번호입니다. 다시 입력해주세요.");
                            }
                        } catch (java.util.InputMismatchException e) {
                            System.out.print("숫자를 입력해주세요.");
                            sc.next();
                        }
                    }
                    System.out.print("변경하실 이름을 입력해주세요: ");
                    String newName = sc.next();
                    monsterballService.changePokemonName(changeIdx, memberId, newName);
                    break;
                case 3:
                    System.out.println(monsterballService.inquiryMyPokemon(memberId));
                    int deleteIdx = -1;
                    maxPokemonIndex = monsterballService.getPokemonCount(memberId);

                    while (deleteIdx < 0 || deleteIdx >= maxPokemonIndex) {
                        System.out.print("오박사님께 보낼 포켓몬의 번호를 입력해주세요 : ");
                        try {
                            deleteIdx = sc.nextInt() - 1;
                            if (deleteIdx < 0 || deleteIdx >= maxPokemonIndex) {
                                System.out.println("올바르지 않은 번호입니다. 다시 입력해주세요.");
                            }
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("숫자를 입력해주세요.");
                            sc.next();
                        }
                    }
                    monsterballService.modifyPokemon(memberId, deleteIdx);
                    break;
                case 4:
                    // 포켓몬 조회를 나가면 리스트 자동저장 및 갱신
                    monsterballService.autoFileSave(memberId);
                    return;
            }
        }
    }
}
