package PokemonBeyond.Skill.run;

import PokemonBeyond.Skill.service.SkillService;

public class Application {
    private static final SkillService ss = new SkillService();

    public static void main(String[] args) {

        // 랜덤스킬
        System.out.println("ddd == " + ss.selectRandomSkill());

    }
}
