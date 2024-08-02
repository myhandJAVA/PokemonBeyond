package PokemonBeyond.Pokemon.service;

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
        ArrayList<Pokemon> allpokemon = pokemonReposiroty.selectAllpokemon();
        IntSupplier randomPokemonNo = ()-> (int)(Math.random() * allpokemon.size());
        Pokemon randomPokemon =  allpokemon.get(randomPokemonNo.getAsInt());
        randomPokemon.setPoekmonSkill(skillService.selectRandomSkill());
        return randomPokemon;
    }
    public boolean isWildPokemonWin(Pokemon wildPokemon,Pokemon myPokemon, int mySkillNo){
        IntSupplier wildPokemonSkillNo = ()-> (int)(Math.random() * 2);
        Skill wildPokemonSkill = wildPokemon.getPoekmonSkill().get(wildPokemonSkillNo.getAsInt());
        int wildPokemonSkillPower = wildPokemonSkill.getSkillPower() * wildPokemon.getPokemonPower();
        int myPokemonSkillPower = myPokemon.getPokemonPower() *
                myPokemon.getPoekmonSkill().get(mySkillNo).getSkillPower();
        if(wildPokemonSkillPower > myPokemonSkillPower) return true;
        else return false;

    }
}
