package PokemonBeyond.Pokemon.repository;

import PokemonBeyond.Pokemon.aggregate.Pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.function.IntSupplier;

public class PokemonRepository {
    private ArrayList<Pokemon> pokemonList;

    public PokemonRepository(){
        File pokemonFile = new File("src/main/java/PokemonBeyond/Pokemon/db/pokemon.dat");
        loadPokemonArrayList(pokemonFile);
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    private void loadPokemonArrayList(File pokemonFile) {
        ObjectInputStream pokemonInput = null;
        try {
            pokemonInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(pokemonFile)));
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
}
