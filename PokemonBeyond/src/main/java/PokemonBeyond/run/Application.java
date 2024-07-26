package PokemonBeyond.run;

import PokemonBeyond.MonsterBall.service.MonsterballService;

import java.util.Scanner;

public class Application {
    private static final MonsterballService monsterballService = new MonsterballService();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("몬스터볼 시스템 Start");
        System.out.println("1. 갖고있는 몬스터볼 보기");
        System.out.println("2. 보유 몬스터 오박사님께 보내기");
        System.out.println("수행하실 기능을 눌러주세요: ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                monsterballService.showMyPokemons();
        }
    }
}
