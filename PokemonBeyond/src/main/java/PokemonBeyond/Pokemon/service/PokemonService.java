package PokemonBeyond.Pokemon.service;

import PokemonBeyond.Encyclopedia.aggregate.Encyclopedia;
import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.repository.PokemonRepository;
import PokemonBeyond.Skill.aggregate.Skill;
import PokemonBeyond.Skill.service.SkillService;


import java.util.ArrayList;
import java.util.function.IntSupplier;

public class PokemonService {
    public PokemonService(){}

    private final PokemonRepository pokemonReposiroty = new PokemonRepository();
    private final SkillService skillService = new SkillService();

    public ArrayList<Pokemon> findAllPokemon(){
        return pokemonReposiroty.selectAllpokemon();
    }

    public Pokemon findPokemon(int pokemonNo){
        Pokemon findedPokemon =  pokemonReposiroty.selectPokemon(pokemonNo);
        if (findedPokemon == null) {
            System.out.println("해당 번호의 포켓몬이 존재하지 않습니다.");
            return null;
        }
        return findedPokemon;
    }
    public Pokemon meetRandomPokemon(){
        ArrayList<Pokemon> pokemonList = pokemonReposiroty.selectAllpokemon();
        IntSupplier randomPokemonNo = ()-> (int)(Math.random() * pokemonList.size())+1;
        Pokemon randomPokemon =  pokemonList.get(randomPokemonNo.getAsInt());
        randomPokemon.setPoekmonSkill(skillService.selectRandomSkill());
        return randomPokemon;
    }
    public boolean isWildPokemonWin(Pokemon wildPokemon,Pokemon myPokemon, int mySkillNo){
        IntSupplier wildPokemonSkillNo = ()-> (int)(Math.random() * 2);
        Skill wildPokemonSkill = wildPokemon.getPoekmonSkill().get(wildPokemonSkillNo.getAsInt());
        System.out.println("야생의 " + wildPokemon.getPokemonName() + "은(는) " + wildPokemonSkill.getSkillName() +
                "을(를) 사용했다!");
        Skill myPokemonSkill = myPokemon.getPoekmonSkill().get(mySkillNo);
        System.out.println(myPokemon.getPokemonName() + "은(는) " + myPokemonSkill.getSkillName() +
                "을(를) 사용했다!");

        int wildPokemonSkillPower = wildPokemonSkill.getSkillPower() * wildPokemon.getPokemonPower();
        int myPokemonSkillPower = myPokemon.getPokemonPower() * myPokemonSkill.getSkillPower();

        if(wildPokemonSkillPower > myPokemonSkillPower) return true;
        else return false;

    }
}
