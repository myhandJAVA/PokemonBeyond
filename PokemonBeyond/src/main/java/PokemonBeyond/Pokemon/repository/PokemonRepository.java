package PokemonBeyond.Pokemon.repository;

import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Skill.aggregate.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class PokemonRepository {
    private ArrayList<Pokemon> pokemonList;
    private ArrayList<Skill> skillList;

    public PokemonRepository(){
        File pokemonFile = new File("src/main/java/PokemonBeyond/Pokemon/db/pokemon.dat");
        loadPokemonToArrayList(pokemonFile);
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    private void loadPokemonToArrayList(File pokemonFile) {
        ObjectInputStream pokemonInput = null;
        try {
            pokemonInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(pokemonFile)));
//            while(true){
//                Pokemon pokemon = (Pokemon)(pokemonInput.readObject());
//                pokemonList.add(pokemon);
            ArrayList<Pokemon> pokemons = (ArrayList<Pokemon>) pokemonInput.readObject();
            pokemonList = pokemons;
            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("포켓몬 로딩 완료");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(pokemonInput != null) pokemonInput.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Pokemon> selectAllpokemon(){
        return pokemonList;
    }

    public Pokemon selectPokemon(int pokemonNo){
        for(Pokemon pokemon: pokemonList){
            if(pokemon.getPokemonNo() == pokemonNo){
                return pokemon;
            }
        }
        return null;
    }

    public Pokemon selectRandomPokemon() {
        IntSupplier randomPokemonNo = ()-> (int)(Math.random() * pokemonList.size())+1;
        Pokemon randomPokemon =  pokemonList.get(randomPokemonNo.getAsInt());
//        randomPokemon.setPoekmonSkill(Skill.giveRandomTwoSkill());
        return randomPokemon;
    }

//    public Pokemon giveRandomTwoSkill(Pokemon pokemon){
//        Supplier<Integer> makeRandomInt = ()->  (int)(Math.random() * pokemonList.size()) + 1;
//        int firstSkillIndex = makeRandomInt.get();
//        int secondSkillIndex = makeRandomInt.get();
//        while(firstSkillIndex == secondSkillIndex){
//            secondSkillIndex = makeRandomInt.get();
//        }
//
//        pokemon.getPoekmonSkill().set(0,skillList.get(firstSkillIndex));
//        pokemon.getPoekmonSkill().set(1,skillList.get(secondSkillIndex));
//
//        return pokemon;
//    }
//
//    public Pokemon meetRandomPokemon(){
//        return new Pokemon();
//    }
}
