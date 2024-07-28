package PokemonBeyond.Skill.service;

import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.service.PokemonService;
import PokemonBeyond.Skill.aggregate.Skill;
import PokemonBeyond.Skill.repository.SkillRepository;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkillService {

    private final SkillRepository sr = new SkillRepository();
    private final PokemonService ps = new PokemonService();
//    private final MonsterBallService mb = new MonsterBallService();
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
//        List<MonsterBall> catchPokemonList = mb.showMyPokemon(id);
        Pokemon pokemonSkillList = ps.findPokemon(id);

        // for문 돌려서 출력하게 하기
        System.out.println("포켓몬: " + pokemonSkillList.getPokemonName() + " , 보유스킬: " + pokemonSkillList.getPoekmonSkill());
    }

    public void saveSkill(int id, int pokemonNo) {
        ArrayList<Skill> saveSkillList = sr.selectRandomSkills();
//        List<MonsterBall> catchPokemonList = mb.showMyPokemon(id);
        Pokemon pokemonSkill = ps.findPokemon(pokemonNo);
        Skill randomSkill;
        do {
            int randomIndex = random.nextInt(saveSkillList.size());
            randomSkill = saveSkillList.get(randomIndex);
        } while (pokemonSkill.getPoekmonSkill().contains(randomSkill));

        int randomIndex = random.nextInt(saveSkillList.size());
        randomSkill = saveSkillList.get(randomIndex);
        pokemonSkill.getPoekmonSkill().add(randomSkill);

        System.out.println("포켓몬스킬 추가 : " + pokemonSkill.getPoekmonSkill());
        for(Skill skill : pokemonSkill.getPoekmonSkill()) {
            System.out.println(" Skiil: " + skill.getSkillName());
        }

    }

    public void deleteSkill(int id, int pokemonNo, String deleteSkillName) {
//        List<MonsterBall> catchPokemonList = mb.showMyPokemon(id);
        Pokemon pokemonSkill = ps.findPokemon(pokemonNo);

        if (pokemonSkill != null) {
            ArrayList<Skill> skills = pokemonSkill.getPoekmonSkill();
            skills.removeIf(skill -> skill.getSkillName().equals(deleteSkillName));
        }
        System.out.println(deleteSkillName + " 스킬이 삭제되었습니다.");
    }

    public void updateSkill(int id) {

    }
}
