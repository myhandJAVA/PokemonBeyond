package PokemonBeyond.Skill.run;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;
import PokemonBeyond.Skill.aggregate.Skill;
import PokemonBeyond.Skill.service.SkillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final SkillService ss = new SkillService();
    static final PokemonService ps = new PokemonService();

    public static void main(String[] args) {

        // 랜덤스킬
        System.out.println("ddd == " + ss.selectRandomSkill());

        Skill skill1 = new Skill();
        skill1.setSkillNo(3);
        skill1.setSkillName("몸통박치기");
        skill1.setSkillPower(56.231231);
        ArrayList<Skill> arrayList = new ArrayList<>();

        Skill skill2 = new Skill();
        skill2.setSkillNo(5);
        skill2.setSkillName("물대포");
        skill2.setSkillPower(56);
        ArrayList<Skill> arrayList1 = new ArrayList<>();
        arrayList1.add(skill1);
        arrayList1.add(skill2);

        arrayList.add(skill1);
        arrayList.add(skill2);
        Pokemon Pokemon = new Pokemon(1, "이상해씨", 318, arrayList );
        Pokemon Pokemon1 = new Pokemon(2, "이상해풀", 405, arrayList1 );
        MyPokemon myPokemon = new MyPokemon(Pokemon, "user01");
        MyPokemon myPokemon1 = new MyPokemon(Pokemon1, "user02");

        ArrayList<MyPokemon> arr = new ArrayList<>();
        arr.add(myPokemon);
        arr.add(myPokemon1);

        System.out.println("내포켓몬: " + myPokemon);

        System.out.println("dkdkdkd: " + myPokemon.getPokemon().getPoekmonSkill());

//        ss.deleteSkill("user01", 1, "몸통박치기", arr);

        ss.selectSkill("user01");

//        ss.saveSkill("user01", 1, arr);

        System.out.println("내포켓몬: " + myPokemon);


//        System.out.println("내포켓몬: " + myPokemon);

//        ss.selectSkill("user01");
//
//        ss.saveSkill(1, 1);
    }

}