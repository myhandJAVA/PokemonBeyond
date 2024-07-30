package PokemonBeyond.Skill.service;

import PokemonBeyond.MonsterBall.aggregate.MyPokemon;
import PokemonBeyond.MonsterBall.service.MonsterballService;
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
    private final MonsterballService ms = new MonsterballService();
    private final Random random = new Random();

    public SkillService() {
    }

    // 조회, 저장, 수정, 삭제, 스킬랜덤추가

    public ArrayList<Skill> selectRandomSkill() {
        ArrayList<Skill> skillList = sr.selectSkills();
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

    public void selectSkill(String memberId) {
        // 회원id를 통해 몬스터볼쪽에서 어떤 포켓몬을 잡았는지 받고 각 포켓몬을 도감에서 스킬을 가져옴
        ArrayList<MyPokemon> catchPokemonList = ms.showMyPokemons(memberId);

        // for문 돌려서 출력하게 하기
        int i = 1;
        for (MyPokemon myPokemon: catchPokemonList) {
            System.out.println(i++ + ". " + "포켓몬: " + myPokemon.getPokemon().getPokemonName()
                    + " , 보유스킬: " + myPokemon.getPokemon().getPoekmonSkill());
        }
    }

    public void saveSkill(String memberId, int pokemonNo) {
        ArrayList<Skill> saveSkillList = sr.selectSkills();
        ArrayList<MyPokemon> catchPokemonList = ms.showMyPokemons(memberId);
        Skill randomSkill;
        for(MyPokemon myPokemon: catchPokemonList) {
            do {
                int randomIndex = random.nextInt(saveSkillList.size());
                randomSkill = saveSkillList.get(randomIndex);
            } while (myPokemon.getPokemon().getPoekmonSkill().contains(randomSkill));

            if(myPokemon.getPokemon().getPokemonNo() == pokemonNo) {
                int randomIndex = random.nextInt(saveSkillList.size());
                randomSkill = saveSkillList.get(randomIndex);
                myPokemon.getPokemon().getPoekmonSkill().add(randomSkill);
                System.out.println(myPokemon.getPokemon().getPokemonName() + "의 새로운 \"" + randomSkill.getSkillName() + "\" 스킬이 추가 되었습니다.");
            }
        }
    }

    public void deleteSkill(String memberId, int pokemonNo, String deleteSkillName) {
        ArrayList<MyPokemon> catchPokemonList = ms.showMyPokemons(memberId);

        String pokemonName = "";
        if (catchPokemonList != null) {
            for(MyPokemon myPokemon: catchPokemonList) {
                if(myPokemon.getPokemon().getPokemonNo() == pokemonNo) {
                    pokemonName = myPokemon.getName();
                    List<Skill> skills = myPokemon.getPokemon().getPoekmonSkill();
                    skills.removeIf(skill -> skill.getSkillName().contains(deleteSkillName));
                }
            }
        }
        System.out.println(pokemonName + "의 " + deleteSkillName + " 스킬이 삭제되었습니다.");
    }

    public void updateSkill(int id) {
        
    }
}
