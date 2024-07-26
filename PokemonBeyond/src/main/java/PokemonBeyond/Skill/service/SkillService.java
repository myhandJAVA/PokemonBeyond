package PokemonBeyond.Skill.service;

import PokemonBeyond.Skill.aggregate.Skill;
import PokemonBeyond.Skill.repository.SkillRepository;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Random;

public class SkillService {

    private final SkillRepository sr = new SkillRepository();
    private final Random random = new Random();
//    private final MemberService ms = new MemberService();

    public SkillService() {
    }

    // 조회, 저장, 수정, 삭제, 스킬랜덤추가

    public ArrayList<Skill> selectRandomSkill() {
        ArrayList<Skill> skillList = sr.selectRandomSkills();
        ArrayList<Skill> randomSkills = new ArrayList<>();

        if (skillList.size() < 2) {
            throw new IllegalArgumentException("Skill list must contain at least 2 skills");
        }

        int firstNum = random.nextInt(skillList.size());
        int secondNum;
        do {
            secondNum = random.nextInt(skillList.size());
        } while (firstNum == secondNum);

        randomSkills.add(skillList.get(firstNum));
        randomSkills.add(skillList.get(secondNum));

        return randomSkills;
    }

    public void selectSkill() {

    }

}
