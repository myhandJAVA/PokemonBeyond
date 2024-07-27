package PokemonBeyond.Skill.service;

import PokemonBeyond.Skill.aggregate.Skill;
import PokemonBeyond.Skill.repository.SkillRepository;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Random;

public class SkillService {

    private final SkillRepository sr = new SkillRepository();
//    private final PokemonService ps = new PokemonService();
    private final Random random = new Random();

    public SkillService() {
    }

    // 조회, 저장, 수정, 삭제, 스킬랜덤추가

    public ArrayList<Skill> selectRandomSkill() {
        ArrayList<Skill> skillList = sr.selectRandomSkills();
        ArrayList<Skill> randomSkills = new ArrayList<>();

        if (skillList.size() < 2) {
            throw new IllegalArgumentException("2개 이상의 스킬이 들어가야 함.");
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

    public void selectSkill(int id) {
        // 회원id를 통해 몬스터볼쪽에서 어떤 포켓몬을 잡았는지 받고 각 포켓몬을 도감에서 스킬을 가져옴
        
    }

}
