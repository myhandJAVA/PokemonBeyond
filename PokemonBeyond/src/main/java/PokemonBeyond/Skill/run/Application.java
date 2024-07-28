package PokemonBeyond.Skill.run;

import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Skill.service.SkillService;

import java.util.Scanner;

public class Application {
    private static final SkillService ss = new SkillService();

    public static void main(String[] args) {

        // 랜덤스킬
        System.out.println("ddd == " + ss.selectRandomSkill());

        ss.selectSkill(1);

        ss.saveSkill(1, 1);
    }

}