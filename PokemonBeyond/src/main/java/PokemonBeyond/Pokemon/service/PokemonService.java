package PokemonBeyond.Pokemon.service;

import PokemonBeyond.Pokemon.aggregate.Pokemon;
import PokemonBeyond.Pokemon.repository.PokemonRepository;

import java.util.ArrayList;

public class PokemonService {
    public PokemonService(){}

    private final PokemonRepository pokemonReposiroty = new PokemonRepository();

    public ArrayList<Pokemon> findAllPokemon(){
        return pokemonReposiroty.selectAllpokemon();
    }

    public Pokemon findPokemon(int pokemonNo){
        return pokemonReposiroty.selectPokemon(pokemonNo);
    }
    public Pokemon meetRandomPokemon(){
        return pokemonReposiroty.selectRandomPokemon();
    }
}
